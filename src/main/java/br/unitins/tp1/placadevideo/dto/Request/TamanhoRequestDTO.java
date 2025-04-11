package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.Tamanho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TamanhoRequestDTO(
    @NotNull(message = "A altura não pode ser nula")
    @Positive(message = "A altura deve ser um valor positivo")
    Integer altura,

    @NotNull(message = "A largura não pode ser nula")
    @Positive(message = "A largura deve ser um valor positivo")
    Integer largura,

    @NotNull(message = "O comprimento não pode ser nulo")
    @Positive(message = "O comprimento deve ser um valor positivo")
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
