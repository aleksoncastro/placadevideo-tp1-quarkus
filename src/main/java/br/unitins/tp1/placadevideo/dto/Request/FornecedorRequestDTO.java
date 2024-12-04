package br.unitins.tp1.placadevideo.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record FornecedorRequestDTO(
    @NotBlank(message = "O campo deve ser informado") 
    String nome,
    @NotBlank(message = "O campo deve ser informado") 
    String cnpj,
    @NotBlank(message = "O campo deve ser informado") 
    String email, 
    @Valid
    List<TelefoneFornecedorRequestDTO> telefones
) {
    
}
