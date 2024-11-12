package br.unitins.tp1.placadevideo.service.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.Request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.model.Pedido;

public interface PedidoService {

    Pedido findById(Long id);

    List<Pedido> findByUsername(String username);

    Pedido create(PedidoRequestDTO dto, String username);

    void cancelarPedido(Long id);

    


}
