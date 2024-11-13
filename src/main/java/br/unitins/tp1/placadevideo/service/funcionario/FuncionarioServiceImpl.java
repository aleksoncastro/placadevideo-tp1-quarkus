package br.unitins.tp1.placadevideo.service.funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import br.unitins.tp1.placadevideo.dto.Request.FuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;
import br.unitins.tp1.placadevideo.model.usuario.Funcionario;
import br.unitins.tp1.placadevideo.repository.endereco.EnderecoRepository;
import br.unitins.tp1.placadevideo.repository.funcionario.FuncionarioRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFuncionarioRepository;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoServiceImpl;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFuncionarioServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Inject
    public EnderecoRepository enderecoRepository;

    @Inject
    public EnderecoServiceImpl enderecoServiceImpl;

    @Inject
    public  TelefoneFuncionarioRepository  telefoneFuncionarioRepository;

    @Inject
    public TelefoneFuncionarioServiceImpl  telefoneFuncionarioServiceImpl;


    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public List<Funcionario> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }

     @Override
    public Funcionario findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Funcionario create(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setDataNascimento(dto.dataNascimento());
        funcionario.setEmail(dto.email());

        // cria o funcionario primeiro
        funcionarioRepository.persist(funcionario);

        funcionario.setTelefones(new ArrayList<>());

        // telefone associado a ele
        for (TelefoneFuncionarioRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneFuncionario telefone = telefoneFuncionarioServiceImpl.create(telefoneDTO);
            funcionario.getTelefones().add(telefone);
        }
        
        //Atualiza o funcionario no banco
        funcionarioRepository.persist(funcionario);

        return funcionario;
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

    @Override
    @Transactional
    public Funcionario update(Long idFuncionario, Long telefoneId, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario);
        if (funcionario == null) {
            throw new EntityNotFoundException("Funcionario não encontrado");
        }

        // Atualiza os dados do funcionario
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setDataNascimento(dto.dataNascimento());
        funcionario.setEmail(dto.email());

    // Atualiza os telefones do funcionario
    for (TelefoneFuncionarioRequestDTO telefoneDTO : dto.telefones()) {
        TelefoneFuncionario telefoneExistente = telefoneFuncionarioRepository.findById(telefoneId);
        if (telefoneExistente != null) {
            telefoneFuncionarioServiceImpl.update(telefoneExistente.getId(), telefoneDTO);
        } else {
            throw new NoSuchElementException("Não encontrado!");
        }
    }

        funcionarioRepository.persist(funcionario);
        return funcionario;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }


}
