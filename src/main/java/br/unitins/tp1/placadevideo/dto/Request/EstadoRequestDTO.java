package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO(
    @NotBlank(message = "O  nome não pode ser nulo")
    @Size(max = 60, message = "O nome deve possuir no máximo 60 caracteres")
    String nome, 
    
    @NotBlank(message = "A sigla não pode ser nula")
    @Size(min = 2, max = 2, message = "A sigla deve possuir 2 caracteres")
    String sigla
) {
    
}
