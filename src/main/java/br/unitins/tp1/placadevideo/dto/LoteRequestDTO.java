package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

public record LoteRequestDTO(
    String codigo,
    Integer quantidade,
    LocalDate dataFabricacao,
    Long idPlacaDeVideo

) {

}
