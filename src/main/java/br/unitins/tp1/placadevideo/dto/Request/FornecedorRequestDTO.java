package br.unitins.tp1.placadevideo.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record FornecedorRequestDTO(
    @NotBlank(message = "O campo deve ser informado") 
    String nome,
    @NotBlank(message = "O campo deve ser informado") 
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos.")
    String cnpj,
    @NotBlank(message = "O campo deve ser informado") 
    @Email(message = "Formato de e-mail inválido.")
    String email, 
    @Valid
    @NotEmpty(message = "Informe ao menos um telefone.")
    List<TelefoneFornecedorRequestDTO> telefones
) {
    
}
