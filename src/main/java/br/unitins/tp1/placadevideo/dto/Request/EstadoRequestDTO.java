package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO(
    @NotNull(message = "O nome deve ser informado (back).")
    @Size(min = 2, max = 60, message = "A o nome deve possuir no mínimo 2 caracteres")
    String nome, 
    
    @NotBlank(message = "A sigla não pode ser nula")
    @Size(min = 2, max = 2, message = "A sigla deve possuir 2 caracteres")
    String sigla
) {
    
}
