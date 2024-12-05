package br.unitins.tp1.placadevideo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.pedido.Pedido;

public record PedidoGeralResponseDTO(
    Long id,
    LocalDateTime data, 
    BigDecimal valorTotal,
    List<UpdateSatusPedidoResponseDTO> statusPedido,
    PagamentoResponseDTO pagamento

    ) {

    public static PedidoGeralResponseDTO valueOf(Pedido pedido){
        return new PedidoGeralResponseDTO(
            pedido.getId(),
            pedido.getData(),
            pedido.getValorTotal(),
            pedido.getListaStatus().stream()
            .map(UpdateSatusPedidoResponseDTO::valueOf)
            .collect(Collectors.toList()),
            PagamentoResponseDTO.valueOf(pedido.getPagamento())
            
        );
    }

}
