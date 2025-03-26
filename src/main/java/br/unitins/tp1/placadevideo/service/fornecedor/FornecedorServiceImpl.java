package br.unitins.tp1.placadevideo.service.fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import br.unitins.tp1.placadevideo.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;
import br.unitins.tp1.placadevideo.repository.fornecedor.FornecedorRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFornecedorRepository;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFornecedorServiceImpl;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID do fornecedor deve ser um valor positivo.");
        }
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new EntityNotFoundException("Fornecedor não encontrado.");
        }
        return fornecedor;
    }

    @Override
    public Fornecedor findByCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new ValidationException("cnpj", "O CNPJ não pode ser nulo ou vazio.");
        }
        Fornecedor fornecedor = fornecedorRepository.findByCnpj(cnpj);
        if (fornecedor == null) {
            throw new EntityNotFoundException("Fornecedor não encontrado com o CNPJ informado.");
        }
        return fornecedor;
    }

    @Override
    public Fornecedor findByIdComTelefones(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID do fornecedor deve ser um valor positivo.");
        }
        Fornecedor fornecedor = fornecedorRepository.findByIdComTelefones(id);
        if (fornecedor == null) {
            throw new EntityNotFoundException("Fornecedor não encontrado.");
        }
        return fornecedor;
    }

    @Override
    public List<Fornecedor> findByNome(String nome, Integer page, Integer pageSize) {
        if (nome == null || nome.isEmpty()) {
            throw new ValidationException("nome", "O nome do fornecedor não pode ser nulo ou vazio.");
        }
        return fornecedorRepository.findByNome(nome).page(page, pageSize).list();
    }

    public List<Fornecedor> findAll(Integer page, Integer pageSize) {
        PanacheQuery<Fornecedor> query = null;
        if (page == null || pageSize == null)
            query = fornecedorRepository.findAll();
        else
            query = fornecedorRepository.findAll().page(page, pageSize);

        return query.list();
    }

    @Override
    @Transactional
    public Fornecedor create(@Valid FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());

        fornecedor.setTelefones(new ArrayList<>());

        fornecedorRepository.persist(fornecedor);

        for (TelefoneFornecedorRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneFornecedor telefone = telefoneFornecedorServiceImpl.create(telefoneDTO);
            fornecedor.getTelefones().add(telefone);
        }

        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public void addTelefone(Long fornecedorId, TelefoneFornecedorRequestDTO dto) {
        Fornecedor fornecedor = findById(fornecedorId);
        TelefoneFornecedor telefone = telefoneFornecedorServiceImpl.create(dto);
        telefoneFornecedorRepository.persist(telefone);
        fornecedor.getTelefones().add(telefone);
    }

    @Override
    @Transactional
    public Fornecedor update(Long fornecedorId, Long telefoneId, @Valid FornecedorRequestDTO dto) {
        Fornecedor fornecedor = findById(fornecedorId);

        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());

        for (TelefoneFornecedorRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneFornecedor telefoneExistente = telefoneFornecedorRepository.findById(telefoneId);
            if (telefoneExistente != null) {
                telefoneFornecedorServiceImpl.update(telefoneExistente.getId(), telefoneDTO);
            } else {
                throw new NoSuchElementException("Telefone não encontrado.");
            }
        }

        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }
        if (!fornecedorRepository.deleteById(id)) {
            throw new EntityNotFoundException("Fornecedor não encontrado para exclusão.");
        }
    }

    @Override
    public long count() {
        return fornecedorRepository.findAll().count();
    }

    @Override
    public long count(String nome) {
        return fornecedorRepository.findByNome(nome).count();
    }
}
