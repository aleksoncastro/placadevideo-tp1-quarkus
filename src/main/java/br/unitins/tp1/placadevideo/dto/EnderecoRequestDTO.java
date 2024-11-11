package br.unitins.tp1.placadevideo.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(
        @NotBlank(message = "O campo deve ser informado") 
        String cep,
        @NotBlank(message = "O campo deve ser informado") 
        String cidade,
        @NotBlank(message = "O campo deve ser informado") 
        String estado,
        @NotBlank(message = "O campo deve ser informado") 
        String bairro,
        @NotBlank(message = "O campo deve ser informado") 
        String numero) {

}
