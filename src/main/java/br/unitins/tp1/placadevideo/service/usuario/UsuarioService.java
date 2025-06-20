package br.unitins.tp1.placadevideo.service.usuario;

//import br.unitins.tp1.placadevideo.dto.UsuarioRequestDTO;
import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EmailPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.SenhaPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.UsernamePatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.UsuarioRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;

public interface UsuarioService {

    Usuario findById(Long id);

    Usuario findByUsernameAndSenha(String username, String senha);

    Usuario findByUsername(String nome);

    Usuario findByCpf(String cpf);

    List<Usuario> findAll();

    Usuario createCliente(UsuarioRequestDTO dto);

    Usuario createFuncionario(UsuarioRequestDTO dto);

    void updateEmail(String username, EmailPatchRequestDTO dto);

    void updateSenha(String username, SenhaPatchRequestDTO dto);
    
    void updateUsername(String username, UsernamePatchRequestDTO dto);

    //Usuario update(Long id, UsuarioRequestDTO dto);

    void delete(Long id);
}
