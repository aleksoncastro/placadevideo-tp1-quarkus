package br.unitins.tp1.placadevideo.dto.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StatusPedidoRequestDTO(
    @NotNull(message = "O id do status não pode ser nulo")
    @Min(value = 1, message = "O valor deve estar entre 1 e 5")
    @Max(value = 5, message = "O valor deve estar entre 1 e 5")
    Integer idStatus
) {

}
