package br.unitins.tp1.placadevideo.dto.Request;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequestDTO(
    BigDecimal valorTotal,
    List<ItemPedidoRequestDTO> listaItemPedido
) {

}
