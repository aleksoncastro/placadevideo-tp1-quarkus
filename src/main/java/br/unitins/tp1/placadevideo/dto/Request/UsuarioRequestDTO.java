package br.unitins.tp1.placadevideo.dto.Request;

import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "O campo nome de usuario deve ser informado") 
        String username,
        @NotBlank(message = "O campo senha deve sr informado") 
        @Size(min = 5, max = 20, message = "A senha deve ter entre 5 e 20 caracteres") 
        String senha,
        Perfil perfil) {

}