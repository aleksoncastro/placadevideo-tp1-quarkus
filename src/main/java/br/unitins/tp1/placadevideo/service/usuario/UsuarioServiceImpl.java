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
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    public Usuario findById(@Valid Long id) {
        Usuario validar = usuarioRepository.findById(id);
        if (validar == null) {
            throw new ValidationException("id", "Id do usuário não encontrado");
        }
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
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
    public Usuario createCliente(@Valid UsuarioRequestDTO dto) {
        validarClienteRequest(dto);

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.USER);
        usuario.setCpf(dto.cpf());
        usuarioRepository.persist(usuario);

        return usuario;
    }

    @Override
    @Transactional
    public Usuario createFuncionario(UsuarioRequestDTO dto) {
        validarFuncionarioRequest(dto);

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));
        usuario.setCpf(dto.cpf());
        usuarioRepository.persist(usuario);

        return usuario;
    }

    private void validarFuncionarioRequest(UsuarioRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "O DTO do usuário não pode ser nulo.");
        }

        if (dto.username() == null || dto.username().isBlank()) {
            throw new ValidationException("username", "O campo 'username' é obrigatório.");
        }

        if (dto.email() == null || dto.email().isBlank()) {
            throw new ValidationException("email", "O campo 'email' é obrigatório.");
        }

        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new ValidationException("senha", "O campo 'senha' é obrigatório.");
        }

        if (dto.idPerfil() == null) {
            throw new ValidationException("idPerfil", "O campo 'idPerfil' é obrigatório.");
        }

        if (dto.cpf() == null || dto.cpf().isBlank()) {
            throw new ValidationException("cpf", "O campo 'cpf' é obrigatório.");
        }

        Usuario validar = usuarioRepository.findByCpf(dto.cpf());
        Usuario validar2 = usuarioRepository.findByEmail(dto.email());
        if (validar != null) {
            throw new ValidationException("cpf", "Já existe um usuário com o CPF informado.");
        }
        if (validar2 != null) {
            throw new ValidationException("email", "Já existe um usuário com o email informado.");
        }
    }

    private void validarClienteRequest(UsuarioRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "O DTO do usuário não pode ser nulo.");
        }

        if (dto.username() == null || dto.username().isBlank()) {
            throw new ValidationException("username", "O campo 'username' é obrigatório.");
        }

        if (dto.email() == null || dto.email().isBlank()) {
            throw new ValidationException("email", "O campo 'email' é obrigatório.");
        }

        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new ValidationException("senha", "O campo 'senha' é obrigatório.");
        }

        if (dto.cpf() == null || dto.cpf().isBlank()) {
            throw new ValidationException("cpf", "O campo 'cpf' é obrigatório.");
        }

        Usuario validar = usuarioRepository.findByCpf(dto.cpf());
        Usuario validar2 = usuarioRepository.findByEmail(dto.email());
        if (validar != null) {
            throw new ValidationException("cpf", "Já existe um usuário com o CPF informado.");
        }
        if (validar2 != null) {
            throw new ValidationException("email", "Já existe um usuário com o email informado.");
        }
    }

    @Override
    @Transactional
    public void updateSenha(String username, SenhaPatchRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByUsername(username);
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
    public void updateEmail(String username, EmailPatchRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw new ValidationException("email", "usuario nao encontrado");

        if (dto.novoEmail().equals(usuario.getEmail()))
            throw new ValidationException("email", "O novo email nao pode ser igual ao atual");

        if (usuarioRepository.findByEmail(dto.novoEmail()) != null && (!dto.novoEmail().equals(usuario.getEmail())))
            throw new ValidationException("novoEmail", "Email ja cadastrado");

        usuario.setEmail(dto.novoEmail());
    }

}
