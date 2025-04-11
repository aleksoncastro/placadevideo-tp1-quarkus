package br.unitins.tp1.placadevideo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record LoteRequestDTO(
    @NotBlank(message = "O campo código deve ser informado.")
    String codigo,

    @NotNull(message = "O campo estoque deve ser informado.")
    @Positive(message = "O estoque deve ser maior que zero.")
    Integer estoque,

    @NotNull(message = "O campo data de fabricação deve ser informado.")
    @PastOrPresent(message = "A data de fabricação não pode ser futura.")
    LocalDate dataFabricacao,

    @NotNull(message = "O campo idPlacaDeVideo deve ser informado.")
    Long idPlacaDeVideo

) {

}
