package br.unitins.tp1.placadevideo.service.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.Request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.StatusPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Response.BoletoResponseDTO;
import br.unitins.tp1.placadevideo.dto.Response.PixResponseDTO;
import br.unitins.tp1.placadevideo.model.pedido.Pedido;

public interface PedidoService {

    Pedido findById(Long id);
    
    List<Pedido> findAll();
    
    List<Pedido> findByItem(Long idPlacaDeVideo);

    List<Pedido> findByStatus(int idStatus);
    
    List<Pedido> findByUsername(String username);

    Pedido create(PedidoRequestDTO dto, String username);

    void cancelarPedido(Long id);

    PixResponseDTO gerarPix(Long idPedido);

    BoletoResponseDTO gerarBoleto(Long idPedido);

    void registrarPagamentoPix(Long idPedido, Long idPix);

    void registrarPagamentoBoleto(Long idPedido, Long idBoleto);

    void registrarPagamentoCartao(Long idPedido, Long idCartao);

    Pedido updateStatusPedido(Long idPedido, StatusPedidoRequestDTO dto);

}
