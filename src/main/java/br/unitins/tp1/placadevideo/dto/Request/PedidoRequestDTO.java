package br.unitins.tp1.placadevideo.dto.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoRequestDTO(
    @NotNull(message = "O valor não pode ser nulo")
    BigDecimal valorTotal,
    List<ItemPedidoRequestDTO> listaItemPedido,
    Long idEndereco,
    @NotNull(message = "O valor não pode ser nulo")
    @Size(min = 1 , max = 3 , message = "O valor deve estar entre 1 e 3")
    Integer tipoPagamento,
    Long idCartao
) {

}
