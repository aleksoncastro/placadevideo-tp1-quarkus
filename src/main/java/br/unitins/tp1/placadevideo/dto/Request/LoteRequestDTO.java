package br.unitins.tp1.placadevideo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoteRequestDTO(
    @NotBlank(message = "O campo codigo deve ser informado.")
    String codigo,
    @NotNull(message = "O campo codigo deve ser informado.")
    @Positive
    Integer estoque,
    @NotNull(message = "O campo data deve ser informado.")
    LocalDate dataFabricacao,
    @NotNull(message = "O campo idPlacaDeVideo deve ser informado.")
    Long idPlacaDeVideo

) {

}
