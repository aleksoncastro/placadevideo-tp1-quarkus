package br.unitins.tp1.placadevideo.dto;

import br.unitins.tp1.placadevideo.model.Perfil;
import br.unitins.tp1.placadevideo.model.Usuario;

public record UsuarioResponseDTO(Long id, String username, String senha, Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getUsername(), 
            usuario.getSenha(),
            usuario.getPerfil());
    }

}
