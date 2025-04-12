package br.unitins.tp1.placadevideo.service.funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import br.unitins.tp1.placadevideo.dto.request.FuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;
import br.unitins.tp1.placadevideo.model.usuario.Funcionario;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import br.unitins.tp1.placadevideo.repository.funcionario.FuncionarioRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFuncionarioRepository;
import br.unitins.tp1.placadevideo.repository.usuario.UsuarioRepository;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFuncionarioServiceImpl;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Inject
    public TelefoneFuncionarioRepository telefoneFuncionarioRepository;

    @Inject
    public TelefoneFuncionarioServiceImpl telefoneFuncionarioServiceImpl;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id);
    }
    
    @Override
    public List<Funcionario> findAll(Integer page, Integer pageSize) {
         PanacheQuery<Funcionario> query = null;
         if (page == null || pageSize == null)
             query = funcionarioRepository.findAll();
         else 
             query = funcionarioRepository.findAll().page(page, pageSize);
 
         return query.list();
     }
 
     @Override
     public List<Funcionario> findByNome(String nome, Integer page, Integer pageSize) {
         return funcionarioRepository.findByNome(nome).page(page, pageSize).list();
     }
 
     public List<Funcionario> findByNome(String nome) {
         return funcionarioRepository.findByNome(nome).list();
     }
 
     @Override
     public long count() {
         return funcionarioRepository.findAll().count();
     }
 
     @Override
     public long count(String nome) {
         return funcionarioRepository.findByNome(nome).count();
     }
    @Override
    public Funcionario findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }


    @Override
    @Transactional
    public Funcionario create(String username, @Valid FuncionarioRequestDTO dto) {

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setDataNascimento(dto.dataNascimento());

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw new ValidationException("usuario", "Usuário associado ao funcionário não encontrado.");
        funcionario.setUsuario(usuario);

        // Criar telefones associados
        funcionario.setTelefones(new ArrayList<>());
        for (TelefoneFuncionarioRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneFuncionario telefone = telefoneFuncionarioServiceImpl.create(telefoneDTO);
            funcionario.getTelefones().add(telefone);
        }

        funcionarioRepository.persist(funcionario);
        return funcionario;
    }

    @Override
    @Transactional
    public Funcionario update(Long idFuncionario, Long telefoneId, @Valid FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario);
        if (funcionario == null)
            throw new EntityNotFoundException("Funcionário não encontrado.");

        // Atualiza os dados do funcionário
        funcionario.setNome(dto.nome());
        funcionario.setDataNascimento(dto.dataNascimento());

        // Atualizar telefones
        for (TelefoneFuncionarioRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneFuncionario telefoneExistente = telefoneFuncionarioRepository.findById(telefoneId);
            if (telefoneExistente != null) {
                telefoneFuncionarioServiceImpl.update(telefoneExistente.getId(), telefoneDTO);
            } else {
                throw new NoSuchElementException("Telefone não encontrado para o ID fornecido.");
            }
        }

        funcionarioRepository.persist(funcionario);
        return funcionario;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (funcionarioRepository.findById(id) == null)
            throw new ValidationException("id", "Funcionário com o ID especificado não encontrado.");
        funcionarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addTelefone(Long funcionarioId, TelefoneFuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionario com ID " + funcionarioId + " não encontrado.");
        }

        TelefoneFuncionario telefone = telefoneFuncionarioServiceImpl.create(dto);
        //telefone.setFuncionario(funcionario);
        telefoneFuncionarioRepository.persist(telefone);
        funcionario.getTelefones().add(telefone);

    }
}
