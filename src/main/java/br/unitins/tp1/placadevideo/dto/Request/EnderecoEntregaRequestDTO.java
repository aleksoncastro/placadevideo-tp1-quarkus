package br.unitins.tp1.placadevideo.dto.Request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoEntregaRequestDTO(
        @NotBlank(message = "O campo deve ser informado") 
        String cep,
        @NotBlank(message = "O campo deve ser informado") 
        String cidade,
        @NotBlank(message = "O campo deve ser informado") 
        String estado,
        @NotBlank(message = "O campo deve ser informado") 
        String bairro,
        @NotBlank(message = "O campo deve ser informado")
        String rua,
        @NotBlank(message = "O campo deve ser informado") 
        String numero) {

}
