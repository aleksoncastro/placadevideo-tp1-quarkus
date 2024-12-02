package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.Tamanho;

public record TamanhoRequestDTO(
    Integer altura,
    Integer largura,
    Integer comprimento
) {
    public Tamanho intoEntity(){
        Tamanho tamanho = new Tamanho();
        tamanho.setAltura(altura());
        tamanho.setLargura(largura());
        tamanho.setComprimento(comprimento());
        return tamanho;
    }

}
