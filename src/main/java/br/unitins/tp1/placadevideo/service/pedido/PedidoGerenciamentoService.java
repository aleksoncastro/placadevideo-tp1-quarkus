package br.unitins.tp1.placadevideo.service.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Pedido;
import br.unitins.tp1.placadevideo.model.StatusPedido;

public interface PedidoGerenciamentoService {
    
    Pedido findById(Long id);

    Pedido atualizarStatus(Long id, StatusPedido novoStatus);

    List<Pedido> findAll();

    void cancelarPedido(Long id);

}
