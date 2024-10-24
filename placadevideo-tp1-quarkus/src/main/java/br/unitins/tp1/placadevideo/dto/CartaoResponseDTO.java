package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

import br.unitins.tp1.placadevideo.model.Cartao;

public record CartaoResponseDTO(
        Long id,
        String numero,
        String titular,
        LocalDate dataValidade,
        String cvv) {
    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getNumero(),
                cartao.getTitular(),
                cartao.getDataValidade(),
                cartao.getCvv()
            );
    }
}
