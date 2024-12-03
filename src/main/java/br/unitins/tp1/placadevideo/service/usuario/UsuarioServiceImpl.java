package br.unitins.tp1.placadevideo.service.usuario;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EmailPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.SenhaPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.UsuarioRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import br.unitins.tp1.placadevideo.repository.usuario.UsuarioRepository;
import br.unitins.tp1.placadevideo.service.hash.HashService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByUsernameAndSenha(String username, String senha) {
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }

    @Override
    public Usuario findByUsername(String nome) {
        return usuarioRepository.findByUsername(nome);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().list();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Usuario createCliente(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.USER);
        usuarioRepository.persist(usuario);

        return usuario;
    }

    @Override
    @Transactional
    public Usuario createFuncionario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));
        usuarioRepository.persist(usuario);

        return usuario;
    }

    @Override
    @Transactional
    public void updateSenha(String email, SenhaPatchRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null)
            throw new ValidationException("email", "usuario nao encontrado");

        if (usuario.getSenha().equals(hashService.getHashSenha(dto.senhaAtual())) == false)
            throw new ValidationException("senhaAtual", "A senha atual esta invalida");

        if (!dto.novaSenha().equals(dto.repetirNovaSenha()))
            throw new ValidationException("repetirNovaSenha", "as senhas nao conferem");

        usuario.setSenha(hashService.getHashSenha(dto.novaSenha()));
    }

    @Override
    @Transactional
    public void updateEmail(String email, EmailPatchRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null)
            throw new ValidationException("email", "usuario nao encontrado");

        if (dto.novoEmail().equals(usuario.getEmail()))
            throw new ValidationException("email", "O novo email nao pode ser igual ao atual");

        if (usuarioRepository.findByEmail(dto.novoEmail()) != null && (!dto.novoEmail().equals(usuario.getEmail())))
            throw new ValidationException("novoEmail", "Email ja cadastrado");

        usuario.setEmail(dto.novoEmail());
    }

}
