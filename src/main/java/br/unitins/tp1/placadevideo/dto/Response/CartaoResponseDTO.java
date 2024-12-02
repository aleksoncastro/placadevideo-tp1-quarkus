package br.unitins.tp1.placadevideo.dto.response;

import java.time.LocalDate;

import br.unitins.tp1.placadevideo.model.usuario.Cartao;

public record CartaoResponseDTO(
        Long id,
        String numero,
        String titular,
        LocalDate dataValidade,
        String cvv,
        String cpf) {
    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getNumero(),
                cartao.getTitular(),
                cartao.getDataValidade(),
                cartao.getCvv(),
                cartao.getCpf()
                );
    }
}
