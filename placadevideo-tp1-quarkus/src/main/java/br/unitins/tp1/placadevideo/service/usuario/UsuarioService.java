package br.unitins.tp1.placadevideo.service.usuario;

//import br.unitins.tp1.placadevideo.dto.UsuarioRequestDTO;
import java.util.List;

import br.unitins.tp1.placadevideo.model.Usuario;

public interface UsuarioService {

    Usuario findById(Long id);

    Usuario findByUsernameAndSenha(String username, String senha);

    Usuario findByUsername(String nome);

    List<Usuario> findAll();

   // Usuario create(UsuarioRequestDTO dto);

    //Usuario update(Long id, UsuarioRequestDTO dto);

    void delete(Long id);
}
