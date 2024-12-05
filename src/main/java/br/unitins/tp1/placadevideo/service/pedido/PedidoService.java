package br.unitins.tp1.placadevideo.service.pedido;

import java.math.BigDecimal;
import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EnderecoEntregaRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.model.pagamento.Boleto;
import br.unitins.tp1.placadevideo.model.pagamento.CartaoPagamento;
import br.unitins.tp1.placadevideo.model.pagamento.Pix;
import br.unitins.tp1.placadevideo.model.pedido.EnderecoEntrega;
import br.unitins.tp1.placadevideo.model.pedido.Pedido;

public interface PedidoService {

    Pedido findById(Long id);
    
    List<Pedido> findAll();
    
    List<Pedido> findByItem(Long idPlacaDeVideo);

    List<Pedido> findByStatus(int idStatus);
    
    List<Pedido> findByUsername(String username);

    Pedido create(PedidoRequestDTO dto, String cpf);

    void cancelarPedido(Long id);

    Pix gerarPix(BigDecimal valor);

    Boleto gerarBoleto(BigDecimal valor);

    void registrarPagamentoPix(Long idPedido, Long idPix);

    void registrarPagamentoBoleto(Long idPedido, Long idBoleto);

    CartaoPagamento registrarPagamentoCartao(Pedido pedido, Long idCartao);

    Pedido updateStatusPedido(Long idPedido, Integer id);

    EnderecoEntrega editEnderecoEntrega(Long idPedido, EnderecoEntregaRequestDTO dto);

}
