package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
    @NotBlank(message = "O campo deve ser informado") 
    String nome,
    @NotBlank(message = "O campo deve ser informado") 
    String cpf,
    @NotBlank(message = "O campo deve ser informado") 
    LocalDate dataNascimento,
    @NotBlank(message = "O campo deve ser informado") 
    String email, 
    EnderecoRequestDTO endereco
) {
    
}
