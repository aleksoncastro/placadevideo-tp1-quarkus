package br.unitins.tp1.placadevideo.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String username,
        String senha,
        String cpf,
        Perfil perfil,
        List<String> listaImagem) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null)
            return null;
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getPerfil(),
                usuario.getListaImagem() != null
                        ? new ArrayList<>(usuario.getListaImagem())
                        : new ArrayList<>()

        );

    }

}
