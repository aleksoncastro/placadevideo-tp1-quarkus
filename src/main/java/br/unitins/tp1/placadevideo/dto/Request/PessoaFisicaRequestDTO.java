package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PessoaFisicaRequestDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome, 
    @NotBlank(message = "O cpf não pode ser nulo.")
    String cpf,
    Integer idSexo
) {
    
}
