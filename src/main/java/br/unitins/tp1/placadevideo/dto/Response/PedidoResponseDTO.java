package br.unitins.tp1.placadevideo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.pedido.Pedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime data, 
    BigDecimal valorTotal,
    List<ItemPedidoResponsetDTO> listaItemPedido,
    EnderecoEntregaResponseDTO enderecoEntrega,
    List<UpdateSatusPedidoResponseDTO> statusPedido,
    PagamentoResponseDTO pagamento

    ) {

    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getData(),
            pedido.getValorTotal(),
            pedido.getListaItemPedido().stream().map(i -> ItemPedidoResponsetDTO.valueOf(i)).toList(),
            EnderecoEntregaResponseDTO.valueOf(pedido.getEnderecoEntrega()),
            pedido.getListaStatus().stream()
            .map(UpdateSatusPedidoResponseDTO::valueOf)
            .collect(Collectors.toList()),
            PagamentoResponseDTO.valueOf(pedido.getPagamento())
            
        );
    }

}
