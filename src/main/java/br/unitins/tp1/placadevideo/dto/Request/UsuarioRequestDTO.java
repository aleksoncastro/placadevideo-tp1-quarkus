package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "O campo nome de usuario deve ser informado") 
        String username,
        @NotBlank(message = "O campo senha deve ser informado") 
        String senha,
        @Valid
        Perfil perfil) {

}
