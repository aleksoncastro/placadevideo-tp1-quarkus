package br.unitins.tp1.placadevideo.service.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.unitins.tp1.placadevideo.dto.Request.ItemPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.StatusPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Response.BoletoResponseDTO;
import br.unitins.tp1.placadevideo.dto.Response.PixResponseDTO;
import br.unitins.tp1.placadevideo.model.pagamento.Boleto;
import br.unitins.tp1.placadevideo.model.pagamento.Cartao;
import br.unitins.tp1.placadevideo.model.pagamento.Pix;
import br.unitins.tp1.placadevideo.model.pedido.ItemPedido;
import br.unitins.tp1.placadevideo.model.pedido.Pedido;
import br.unitins.tp1.placadevideo.model.pedido.StatusPedido;
import br.unitins.tp1.placadevideo.model.pedido.UpdateStatusPedido;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import br.unitins.tp1.placadevideo.repository.pagamento.PagamentoRepository;
import br.unitins.tp1.placadevideo.repository.pedido.PedidoRepository;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import br.unitins.tp1.placadevideo.service.usuario.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public LoteService loteService;

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public PagamentoRepository pagamentoRepository;

    @Inject
    public CartaoRepository cartaoRepository;

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findByUsername(String username) {
        // buscar pelo username
        return pedidoRepository.findByUsername(username);

    }

    @Override
    @Transactional
    public Pedido create(PedidoRequestDTO dto, String username) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setUsuario(usuarioService.findByUsername(username));

        pedido.setListaItemPedido(new ArrayList<ItemPedido>());
        adicionarItens(dto, pedido);

        BigDecimal valorTotal = calcularTotal(pedido.getListaItemPedido());

        pedido.setValorTotal(valorTotal);

        List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(1));
        pedido.setListaStatus(updateStatusPedidos);

        pedidoRepository.persist(pedido);

        return pedido;
    }

    private BigDecimal calcularTotal(List<ItemPedido> itensPedidos) {
        if (itensPedidos == null || itensPedidos.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido itemPedido : itensPedidos) {
            // Converte a quantidade de Integer para BigDecimal
            BigDecimal quantidade = BigDecimal.valueOf(itemPedido.getQuantidade());
            // Multiplica o preço pelo quantidade
            total = total.add(itemPedido.getPreco().multiply(quantidade));
        }

        return total;
    }

    private void adicionarItens(PedidoRequestDTO dto, Pedido pedido) {
        for (ItemPedidoRequestDTO itemDTO : dto.listaItemPedido()) {
            ItemPedido item = new ItemPedido();
            Lote lote = loteService.findByIdPlacaDeVideo(itemDTO.idProduto());
            item.setLote(lote);
            item.setPreco(itemDTO.preco());
            item.setQuantidade(itemDTO.quantidade());

            // atualizar o estoque
            lote.setEstoque(lote.getEstoque() - itemDTO.quantidade());

            pedido.getListaItemPedido().add(item);
        }
    }

    private UpdateStatusPedido createStatusPedido(Integer id) {
        UpdateStatusPedido status = new UpdateStatusPedido();

        status.setStatus(StatusPedido.valueOf(id));
        status.setDataAtualizacao(LocalDateTime.now());

        return status;
    }

    @Override
    
    public Pedido updateStatusPedido(Long idPedido, StatusPedidoRequestDTO dto) {
        Pedido p = pedidoRepository.findById(idPedido);

        List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(dto.idStatus()));
        p.setListaStatus(updateStatusPedidos);
        
        pedidoRepository.persist(p);

        return p;
    }

    @Override
    @Transactional
    public void cancelarPedido(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findByIdOptional(id);

        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();

            // Verifica se o último status do pedido é AGUARDANDO_PAGAMENTO
            if (pedido.getListaStatus().isEmpty() ||
                    !pedido.getListaStatus().get(pedido.getListaStatus().size() - 1).getStatus()
                            .equals(StatusPedido.ENTREGUE)) {
                throw new RuntimeException("O pedido não pode ser cancelado porque já foi entregue.");
            }

            pedido.setListaStatus(Arrays.asList(createStatusPedido(5))); // StatusPedido.CANCELADO
            pedidoRepository.persist(pedido);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    @Override
    @Transactional
    public PixResponseDTO gerarPix(Long idPedido) {
        BigDecimal valor = pedidoRepository.findById(idPedido).getValorTotal();

        Pix pix = new Pix();
        pix.setValor(valor);
        pix.setChave("2cbec47f-c5a7-4841-ad92-e0d3021aeab8");
        pix.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(pix);

        return PixResponseDTO.valueOf(pix);
    }

    @Override
    @Transactional
    public BoletoResponseDTO gerarBoleto(Long idPedido) {
        BigDecimal valor = pedidoRepository.findById(idPedido).getValorTotal();

        Boleto boleto = new Boleto();
        boleto.setValor(valor);
        boleto.setCodigo(UUID.randomUUID().toString());

        return BoletoResponseDTO.valueOf(boleto);
    }

    @Override
    @Transactional
    public void registrarPagamentoPix(Long idPedido, Long idPix) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            p.setPagamento(pagamentoRepository.findById(idPix));

            List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(2));
            p.setListaStatus(updateStatusPedidos);
            pedidoRepository.persist(p);
        }

    }

    @Override
    @Transactional
    public void registrarPagamentoBoleto(Long idPedido, Long idBoleto) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            p.setPagamento(pagamentoRepository.findById(idBoleto));

            List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(2));
            p.setListaStatus(updateStatusPedidos);
            pedidoRepository.persist(p);
        }

    }

    @Override
    @Transactional
    public void registrarPagamentoCartao(Long idPedido, Long idCartao) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            Cartao c = cartaoRepository.findById(idCartao);
            c.setValor(p.getValorTotal());

            pagamentoRepository.persist(c);
            p.setPagamento(c);

            List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(2));
            p.setListaStatus(updateStatusPedidos);
            pedidoRepository.persist(p);
        }
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll().list();
    }

    @Override
    public List<Pedido> findByItem(Long idPlacaDeVideo) {
        return pedidoRepository.findByItem(idPlacaDeVideo);
    }

    @Override
    public List<Pedido> findByStatus(int idStatus) {
        return pedidoRepository.findByStatus(idStatus);
    }

}