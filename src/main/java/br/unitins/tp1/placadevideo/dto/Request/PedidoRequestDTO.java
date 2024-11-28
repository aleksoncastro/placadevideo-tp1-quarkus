package br.unitins.tp1.placadevideo.dto.Request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoRequestDTO(
    @NotNull(message = "O valor não pode ser nulo")
    BigDecimal valorTotal,
    @Valid
    List<ItemPedidoRequestDTO> listaItemPedido,
    @Valid
    EnderecoEntregaRequestDTO enderecoEntrega,
    @NotNull(message = "O valor não pode ser nulo")
    @Size(min = 1 , max = 3 , message = "O valor deve estar entre 1 e 3")
    Integer tipoPagamento
) {

}
