package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.Estado;

public record EstadoResponseDTO(Long id, String nome, String sigla) {

    public static EstadoResponseDTO valueOf(Estado estado){
        return new EstadoResponseDTO(estado.getId(), estado.getNome(), estado.getSigla());
    }

}
