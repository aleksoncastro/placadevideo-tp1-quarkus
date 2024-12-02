package br.unitins.tp1.placadevideo.service.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.unitins.tp1.placadevideo.dto.request.ItemPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.PedidoRequestDTO;
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
import br.unitins.tp1.placadevideo.model.usuario.Endereco;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.pagamento.PagamentoRepository;
import br.unitins.tp1.placadevideo.repository.pedido.PedidoRepository;
import br.unitins.tp1.placadevideo.service.cartao.CartaoService;
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

    @Inject
    public CartaoService cartaoService;

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
        pedido.setCliente(clienteService.findByUsername(username));

        pedido.setListaItemPedido(new ArrayList<>());
        adicionarItens(dto, pedido);

        if (dto.idEndereco() == null) {
            throw new ValidationException("idEndereco",
                    "O campo idEndereco é obrigatório.");
        }

        pedido.setEnderecoEntrega(getEnderecoEntrega(pedido.getCliente(), dto.idEndereco()));

        BigDecimal valorTotal = calcularTotal(pedido);

        if (dto.valorTotal().compareTo(valorTotal) != 0) {
            throw new ValidationException("valorTotal", "O valorTotal recebido não corresponde com o valor calculado.");
        }

        pedido.setValorTotal(valorTotal);

        if (dto.tipoPagamento() < 1 || dto.tipoPagamento() > 3) {
            throw new ValidationException("tipoPagamento", "O tipoPagamento é inválido");
        }

        switch (dto.tipoPagamento()) {
            case 1:
                pedido.setPagamento(gerarPix(valorTotal));
                createStatusPedido(pedido, 1);
                break;
            case 2:
                pedido.setPagamento(gerarBoleto(valorTotal));
                createStatusPedido(pedido, 1);
                break;
            case 3:
                if (pedido.getCliente().getCartoes().isEmpty()) {
                    throw new NullPointerException("O cliente não possue cartoes");
                }
                pedido.setPagamento(registrarPagamentoCartao(pedido, dto.idCartao()));
                createStatusPedido(pedido, 2);

                break;
            default:
                break;
        }
        pedidoRepository.persist(pedido);
        return pedido;
    }

    private EnderecoEntrega getEnderecoEntrega(Cliente cliente, Long idEndereco) {
        Endereco endereco = cliente.getEnderecos()
                .stream()
                .filter(e -> e.getId().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new ValidationException("idEndereco", "Endereco nao encontrado"));

        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setBairro(endereco.getBairro());
        enderecoEntrega.setNumero(endereco.getNumero());
        enderecoEntrega.setCep(endereco.getCep());
        enderecoEntrega.setRua(endereco.getRua());
        enderecoEntrega.setEstado(endereco.getEstado());
        enderecoEntrega.setCidade(endereco.getCidade());

        return enderecoEntrega;
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
            if (lote == null) {
                throw new ValidationException("idProduto", "Por favor informe o ID de algum produto válido.");
            }
    
            int quantidadeEstoque = calcularQuantidadeEstoque(itemDTO.idProduto());
            if (quantidadeEstoque < itemDTO.quantidade()) {
                throw new ValidationException("quantidade", "Quantidade em estoque insuficiente.");
            }
    
            BigDecimal precoTotal = BigDecimal.ZERO;
            int quantidadeRestante = itemDTO.quantidade();
    
            while (quantidadeRestante > 0) {
                lote = loteService.findByIdPlacaDeVideo(itemDTO.idProduto());
                if (lote == null || lote.getEstoque() <= 0) {
                    throw new ValidationException("estoque", "Estoque insuficiente para completar o pedido.");
                }
    
                int quantidadeUsada = Math.min(lote.getEstoque(), quantidadeRestante);
    
                lote.setEstoque(lote.getEstoque() - quantidadeUsada);
    
                BigDecimal quantidadeUsadaBD = BigDecimal.valueOf(quantidadeUsada);
                precoTotal = precoTotal.add(quantidadeUsadaBD.multiply(lote.getPlacaDeVideo().getPreco()));
    
                quantidadeRestante -= quantidadeUsada;
            }
    
            item.setLote(lote);
            item.setPreco(precoTotal);
            item.setQuantidade(itemDTO.quantidade());
    
            pedido.getListaItemPedido().add(item);
        }
    }
    

    private int calcularQuantidadeEstoque(Long idProduto) {
        return loteService.findByIdPlacaDeVideoQtdeTotal(idProduto)
                .stream()
                .mapToInt(Lote::getEstoque)
                .sum();
    }

    private void createStatusPedido(Pedido pedido, Integer id) {
        UpdateStatusPedido status = new UpdateStatusPedido();

        if (pedido.getListaStatus() == null) {
            pedido.setListaStatus(new ArrayList<>());
        }

        status.setStatus(StatusPedido.valueOf(id));
        status.setDataAtualizacao(LocalDateTime.now());
        pedido.getListaStatus().add(status);
    }

    @Override
    @Transactional
    public Pedido updateStatusPedido(Long idPedido, Integer id) {
        Pedido p = pedidoRepository.findById(idPedido);
        
        createStatusPedido(p, id);

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

            createStatusPedido(pedido, 5); // StatusPedido.CANCELADO
            pedidoRepository.persist(pedido);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    @Override
    @Transactional
    public Pix gerarPix(BigDecimal valor) {

        Pix pix = new Pix();
        pix.setValor(valor);
        pix.setChave("2cbec47f-c5a7-4841-ad92-e0d3021aeab8");
        pix.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(pix);

        return pix;
    }

    @Override
    @Transactional
    public Boleto gerarBoleto(BigDecimal valor) {

        Boleto boleto = new Boleto();
        boleto.setValor(valor);
        boleto.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(boleto);

        return boleto;
    }

    @Override
    @Transactional
    public void registrarPagamentoPix(Long idPedido, Long idPix) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            p.setPagamento(pagamentoRepository.findById(idPix));

            p.setPagamento(pagamentoRepository.findById(idPix));

            createStatusPedido(p, 2);
            
        }

    }

    @Override
    @Transactional
    public void registrarPagamentoBoleto(Long idPedido, Long idBoleto) {
        Pedido p = pedidoRepository.findById(idPedido);
        if (p != null) {
            p.setPagamento(pagamentoRepository.findById(idBoleto));

            createStatusPedido(p, 2);
        }

    }

    @Override
    @Transactional
    public CartaoPagamento registrarPagamentoCartao(Pedido pedido, Long idCartao) {
        Cartao cartao = cartaoService.findById(idCartao);
        if (cartao == null)
            throw new ValidationException("idCartao", "Cartao nao encontrado");

        CartaoPagamento cartaoPagamento = new CartaoPagamento();

        cartaoPagamento.setTitular(cartao.getTitular());
        cartaoPagamento.setNumero(cartao.getNumero());
        cartaoPagamento.setCvv(cartao.getCvv());
        cartaoPagamento.setDataValidade(cartao.getDataValidade());
        cartaoPagamento.setCpf(cartao.getCpf());
        cartaoPagamento.setValor(pedido.getValorTotal());

        pagamentoRepository.persist(cartaoPagamento);

        pedido.setPagamento(cartaoPagamento);

        return cartaoPagamento;
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