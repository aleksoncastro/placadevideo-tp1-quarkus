package br.unitins.tp1.placadevideo.dto.Request;

import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "O campo nome de usuario deve ser informado") 
        String username,
        @NotBlank(message = "O campo senha deve ser informado") 
        String senha,
        Perfil perfil) {

}
