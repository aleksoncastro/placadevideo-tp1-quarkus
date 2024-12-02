package br.unitins.tp1.placadevideo.dto.request;

import java.time.LocalDate;

public record LoteRequestDTO(
    String codigo,
    Integer estoque,
    LocalDate dataFabricacao,
    Long idPlacaDeVideo

) {

}
