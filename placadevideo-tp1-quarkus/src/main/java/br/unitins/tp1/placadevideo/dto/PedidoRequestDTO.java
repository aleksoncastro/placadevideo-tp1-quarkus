package br.unitins.tp1.placadevideo.dto;

import java.util.List;

public record PedidoRequestDTO(
    Double valorTotal,
    List<ItemPedidoRequestDTO> listaItemPedido
) {

}
