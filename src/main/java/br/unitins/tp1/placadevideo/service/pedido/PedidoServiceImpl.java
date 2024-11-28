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
import br.unitins.tp1.placadevideo.dto.Response.BoletoResponseDTO;
import br.unitins.tp1.placadevideo.dto.Response.PixResponseDTO;
import br.unitins.tp1.placadevideo.model.pagamento.Boleto;
import br.unitins.tp1.placadevideo.model.pagamento.CartaoPagamento;
import br.unitins.tp1.placadevideo.model.pagamento.Pix;
import br.unitins.tp1.placadevideo.model.pedido.EnderecoEntrega;
import br.unitins.tp1.placadevideo.model.pedido.ItemPedido;
import br.unitins.tp1.placadevideo.model.pedido.Pedido;
import br.unitins.tp1.placadevideo.model.pedido.StatusPedido;
import br.unitins.tp1.placadevideo.model.pedido.UpdateStatusPedido;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
import br.unitins.tp1.placadevideo.model.usuario.Cartao;
import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.pagamento.PagamentoRepository;
import br.unitins.tp1.placadevideo.repository.pedido.PedidoRepository;
import br.unitins.tp1.placadevideo.service.cliente.ClienteService;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
import br.unitins.tp1.placadevideo.service.usuario.UsuarioService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public PlacaDeVideoService placaDeVideoService;

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

    @Inject
    public EnderecoService enderecoService;

    @Inject
    public ClienteService clienteService;

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
    public Pedido create(@Valid PedidoRequestDTO dto, String username) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setUsuario(usuarioService.findByUsername(username));

        pedido.setListaItemPedido(new ArrayList<>());
        adicionarItens(dto, pedido);

        if (dto.enderecoEntrega() == null) {
            throw new ValidationException("enderecoEntrega",
                    "O campo enderecoEntrega é obrigatório para tipoEndereco.");
        }

        EnderecoEntrega novoEndereco = new EnderecoEntrega();
        novoEndereco.setCep(dto.enderecoEntrega().cep());
        novoEndereco.setEstado(dto.enderecoEntrega().estado());
        novoEndereco.setCidade(dto.enderecoEntrega().cidade());
        novoEndereco.setRua(dto.enderecoEntrega().rua());
        novoEndereco.setNumero(dto.enderecoEntrega().numero());

        pedido.setEnderecoEntrega(novoEndereco);

        BigDecimal valorTotal = calcularTotal(pedido);
        if (dto.valorTotal() != valorTotal) {
            throw new ValidationException("valorTotal", "O valorTotal recebido não corresponde com o valor calculado.");
        }

        pedido.setValorTotal(valorTotal);

        List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(1));
        pedido.setListaStatus(updateStatusPedidos);

        if (dto.tipoPagamento() < 1 || dto.tipoPagamento() > 3) {
            throw new ValidationException("tipoPagamento", "O tipoPagamento é inválido");
        }

        switch (dto.tipoPagamento()) {
            case 1:
                gerarPix(valorTotal);
                break;
            case 2:
                gerarBoleto(valorTotal);
                break;
            case 3:
            Cliente cliente = clienteService.findByUsername(username);
            if (cliente.getCartoes().isEmpty()) {
                throw new NullPointerException("O cliente não possue cartoes");
            }

            Cartao c = cartaoRepository.findFirstCartaoByUsername(username);

            CartaoPagamento cartao = new CartaoPagamento();
            cartao.setCvv(c.getCvv());
            cartao.setDataValidade(c.getDataValidade());
            cartao.setTitular(c.getTitular());
            cartao.setNumero(c.getNumero());
            cartao.setValor(valorTotal);
            
            updateStatusPedidos.clear();
            updateStatusPedidos.add(createStatusPedido(2));

                break;
            default:
                break;
        }
        pedidoRepository.persist(pedido);
        return pedido;
    }

    private BigDecimal calcularTotal(Pedido pedido) {
        return pedido.getListaItemPedido()
                .stream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void adicionarItens(PedidoRequestDTO dto, Pedido pedido) {
        for (ItemPedidoRequestDTO itemDTO : dto.listaItemPedido()) {
            ItemPedido item = new ItemPedido();
            item.setPreco(BigDecimal.ZERO);
            Lote lote = loteService.findByIdPlacaDeVideo(itemDTO.idProduto());
            if (lote == null)
                throw new ValidationException("idProduto", "Por favor informe o id de algum produto valido");

            int quantidadeEstoque = calcularQuantidadeEstoque(itemDTO.idProduto());
            if (quantidadeEstoque < itemDTO.quantidade())
                throw new ValidationException("quantidade", "quantidade em estoque insuficiente");

            BigDecimal precoTotal = BigDecimal.ZERO;
            int quantidadeRestante = itemDTO.quantidade();

            while (quantidadeRestante > 0) {
                lote = loteService.findByIdPlacaDeVideo(itemDTO.idProduto());

                int quantidadeUsada = Math.min(lote.getEstoque(), quantidadeRestante);

                lote.setEstoque(lote.getEstoque() - quantidadeUsada);

                BigDecimal quantidadeUsadaBD = new BigDecimal(quantidadeUsada);

                precoTotal = precoTotal.add(quantidadeUsadaBD.multiply(lote.getPlacaDeVideo().getPreco()));

                quantidadeRestante -= quantidadeUsada;
            }
            item.setLote(lote);
            item.setPreco(precoTotal);
            item.setQuantidade(itemDTO.quantidade());

            // atualizar o estoque

            pedido.getListaItemPedido().add(item);

        }
    }

    private int calcularQuantidadeEstoque(Long idProduto) {
        return loteService.findByIdPlacaDeVideoQtdeTotal(idProduto)
                .stream()
                .mapToInt(Lote::getEstoque)
                .sum();
    }

    private UpdateStatusPedido createStatusPedido(Integer id) {
        UpdateStatusPedido status = new UpdateStatusPedido();

        status.setStatus(StatusPedido.valueOf(id));
        status.setDataAtualizacao(LocalDateTime.now());

        return status;
    }

    @Override
    @Transactional
    public Pedido updateStatusPedido(Long idPedido, Integer id) {
        Pedido p = pedidoRepository.findById(idPedido);

        List<UpdateStatusPedido> updateStatusPedidos = Arrays.asList(createStatusPedido(id));
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
    public PixResponseDTO gerarPix(BigDecimal valor) {

        Pix pix = new Pix();
        pix.setValor(valor);
        pix.setChave("2cbec47f-c5a7-4841-ad92-e0d3021aeab8");
        pix.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(pix);

        return PixResponseDTO.valueOf(pix);
    }

    @Override
    @Transactional
    public BoletoResponseDTO gerarBoleto(BigDecimal valor) {

        Boleto boleto = new Boleto();
        boleto.setValor(valor);
        boleto.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(boleto);

        return BoletoResponseDTO.valueOf(boleto);
    }

    @Override
    @Transactional
    public void registrarPagamentoPix(Long idPedido, Long idPix) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            p.setPagamento(pagamentoRepository.findById(idPix));

            p.setPagamento(pagamentoRepository.findById(idPix));

            List<UpdateStatusPedido> novosStatus = Arrays.asList(createStatusPedido(2));
            p.getListaStatus().clear();
            p.getListaStatus().addAll(novosStatus);

        }

    }

    @Override
    @Transactional
    public void registrarPagamentoBoleto(Long idPedido, Long idBoleto) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            p.setPagamento(pagamentoRepository.findById(idBoleto));

            List<UpdateStatusPedido> novosStatus = Arrays.asList(createStatusPedido(2));
            p.getListaStatus().clear();
            p.getListaStatus().addAll(novosStatus);
        }

    }


    @Transactional
    @Scheduled(every = "1m")
    public void verificarPagamentoNull() {
        List<Pedido> pedido = pedidoRepository.findPedidoPagamentoNull();
        
        for (Pedido p : pedido) {
            if (LocalDateTime.now().isAfter(p.getData().plusMinutes(5))) {
                updateStatusPedido(p.getId(), 5); // altera o status para cancelado
                for (ItemPedido item : p.getListaItemPedido()) {
                    Lote l = item.getLote();
                    Integer estoque = l.getEstoque();

                    l.setEstoque(estoque + item.getQuantidade());
                }
            pedidoRepository.delete(p);
            }
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