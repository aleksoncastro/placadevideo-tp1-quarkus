package br.unitins.tp1.placadevideo.dto.Request;

import java.util.List;

import jakarta.validation.Valid;

public record PedidoRequestDTO(
    @Valid
    List<ItemPedidoRequestDTO> listaItemPedido,
    @Valid
    EnderecoEntregaRequestDTO enderecoEntrega
) {

}
