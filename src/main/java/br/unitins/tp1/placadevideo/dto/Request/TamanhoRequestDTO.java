package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.Tamanho;
import jakarta.validation.constraints.NotNull;

public record TamanhoRequestDTO(
    @NotNull(message = "Altura não pode ser nula") Integer altura,
    @NotNull(message = "Largura não pode ser nula") Integer largura,
    @NotNull(message = "Comprimento não pode ser nulo") Integer comprimento
) {
    public Tamanho intoEntity(){
        Tamanho tamanho = new Tamanho();
        tamanho.setAltura(altura());
        tamanho.setLargura(largura());
        tamanho.setComprimento(comprimento());
        return tamanho;
    }

}
