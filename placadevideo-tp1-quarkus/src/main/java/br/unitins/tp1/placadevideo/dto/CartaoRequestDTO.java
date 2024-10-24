package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

public record CartaoRequestDTO(
    String numero,
    String titular,
    LocalDate dataValidade,
    String cvv
) {

}
