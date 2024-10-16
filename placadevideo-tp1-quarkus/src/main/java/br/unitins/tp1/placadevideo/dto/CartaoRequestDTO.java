package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

public record CartaoRequestDTO(
    String numero,
    String Titular,
    LocalDate dataValidade,
    String cvv
) {

}
