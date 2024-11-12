package br.unitins.tp1.placadevideo.dto.Request;

import java.util.List;

public record PedidoRequestDTO(
    Double valorTotal,
    List<ItemPedidoRequestDTO> listaItemPedido
) {

}
