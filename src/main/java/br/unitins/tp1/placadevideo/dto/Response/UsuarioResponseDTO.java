package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;

public record UsuarioResponseDTO(Long id, String username, String senha, String email, Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getUsername(), 
            usuario.getSenha(),
            usuario.getEmail(),
            usuario.getPerfil());
    }

}
