package br.unitins.tp1.placadevideo.service.fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import br.unitins.tp1.placadevideo.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;
import br.unitins.tp1.placadevideo.repository.fornecedor.FornecedorRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFornecedorRepository;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFornecedorServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    public FornecedorRepository fornecedorRepository;

    @Inject
    public TelefoneFornecedorServiceImpl telefoneFornecedorServiceImpl;

    @Inject
    public TelefoneFornecedorRepository telefoneFornecedorRepository;


    @Override
    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id);
    }
    
    @Override
    public Fornecedor findByCnpj(String cnpj){
        return fornecedorRepository.findByCnpj(cnpj);
    }

    @Override
    public Fornecedor findByIdComTelefones(Long id) {
        return fornecedorRepository.findByIdComTelefones(id);
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll().list();
    }

    @Override
    @Transactional
    public Fornecedor create(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());

        // cria o fornecedor primeiro
        fornecedorRepository.persist(fornecedor);

        fornecedor.setTelefones(new ArrayList<>());

        // telefone associado a ele
       for (TelefoneFornecedorRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneFornecedor telefone = telefoneFornecedorServiceImpl.create(telefoneDTO);
            fornecedor.getTelefones().add(telefone);
        }
        
        
        //Atualiza o fornecedor no banco
        fornecedorRepository.persist(fornecedor);

        return fornecedor;
    }

    @Override
    @Transactional
    public void addTelefone(Long fornecedorId, TelefoneFornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor com ID " + fornecedorId + " não encontrado.");
        }

        TelefoneFornecedor telefone = telefoneFornecedorServiceImpl.create(dto);
        telefoneFornecedorRepository.persist(telefone);
        fornecedor.getTelefones().add(telefone);

    }

    @Override
    @Transactional
    public Fornecedor update(Long fornecedorId, Long telefoneId, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor == null) {
            throw new EntityNotFoundException("Fornecedor não encontrado");
        }

        // Atualiza os dados do fornecedor
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());
        
        // Atualiza os telefones do fornecedor
    for (TelefoneFornecedorRequestDTO telefoneDTO : dto.telefones()) {
        TelefoneFornecedor telefoneExistente = telefoneFornecedorRepository.findById(telefoneId);
        if (telefoneExistente != null) {
            telefoneFornecedorServiceImpl.update(telefoneExistente.getId(), telefoneDTO);
        } else {
            throw new NoSuchElementException("Não encontrado!");
        }
    }

        // Persistindo as alterações do fornecedor
        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    


   

}
