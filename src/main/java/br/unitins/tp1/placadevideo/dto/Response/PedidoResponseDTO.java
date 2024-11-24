package br.unitins.tp1.placadevideo.dto.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.placadevideo.model.pedido.Pedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime data, 
    BigDecimal valorTotal,
    List<ItemPedidoResponsetDTO> listaItemPedido) {

    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getData(),
            pedido.getValorTotal(),
            pedido.getListaItemPedido().stream().map(i -> ItemPedidoResponsetDTO.valueOf(i)).toList());
    }

}
