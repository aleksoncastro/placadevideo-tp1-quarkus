package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
    
    @NotBlank(message = "O campo nome de usuário deve ser informado.")
    @Size(min = 3, max = 30, message = "O nome de usuário deve ter entre 3 e 30 caracteres.")
    String username,

    @NotBlank(message = "O campo email deve ser informado.")
    @Email(message = "O email informado não é válido.")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres.")
    String email,

    @NotBlank(message = "O campo senha deve ser informado.")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres.")
    String senha,

    @NotBlank(message = "O campo CPF deve ser informado.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    String cpf

) {}
