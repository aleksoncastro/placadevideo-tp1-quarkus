package br.unitins.tp1.placadevideo.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record FornecedorRequestDTO(
    @NotBlank(message = "O campo deve ser informado") 
    String nome,
    @NotBlank(message = "O campo deve ser informado") 
    String cnpj,
    @NotBlank(message = "O campo deve ser informado") 
    String email, 
    List<TelefoneFornecedorRequestDTO> telefones
) {
    
}
