package br.unitins.tp1.placadevideo.dto.response;

import java.time.LocalDateTime;

import br.unitins.tp1.placadevideo.model.pedido.StatusPedido;
import br.unitins.tp1.placadevideo.model.pedido.UpdateStatusPedido;

public record UpdateSatusPedidoResponseDTO(
    StatusPedido status,
    LocalDateTime dataAtualizacao
) {
    public static UpdateSatusPedidoResponseDTO valueOf(UpdateStatusPedido status) {
        return new UpdateSatusPedidoResponseDTO(
            status.getStatus(),
            status.getDataAtualizacao()
        );
    }
}