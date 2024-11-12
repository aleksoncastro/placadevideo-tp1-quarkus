package br.unitins.tp1.placadevideo.dto.Request;

import java.time.LocalDate;

public record LoteRequestDTO(
    String codigo,
    Integer estoque,
    LocalDate dataFabricacao,
    Long idPlacaDeVideo

) {

}
