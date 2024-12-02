package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.placadevideo.Tamanho;

public record TamanhoResponseDTO(
    Long Id,
    Integer altura,
    Integer largura,
    Integer comprimento
) {
    public static  TamanhoResponseDTO valueOf(Tamanho tamanho) {
        return new TamanhoResponseDTO( 
        tamanho.getId(),
        tamanho.getAltura(),
        tamanho.getLargura(),
        tamanho.getComprimento()
        );
    } 

}
