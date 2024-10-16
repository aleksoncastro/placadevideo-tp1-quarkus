package br.unitins.tp1.placadevideo.service.fornecedor;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.dto.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.Telefone;
import br.unitins.tp1.placadevideo.repository.fornecedor.FornecedorRepository;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    public FornecedorRepository fornecedorRepository;

    @Inject
    public EnderecoServiceImpl enderecoServiceImpl;

    @Override
    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id);
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
        fornecedor.setTelefones(getTelefones(dto));
        
        //Atualiza o fornecedor no banco
        fornecedorRepository.persist(fornecedor);

        return fornecedor;
    }

    @Override
    @Transactional
    public Fornecedor update(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new EntityNotFoundException("Fornecedor não encontrado");
        }

        // Atualiza os dados do fornecedor
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());
        // Remove os antigos
        fornecedor.getTelefones().clear();

        fornecedor.setTelefones(getTelefones(dto));
        // Persistindo as alterações do fornecedor
        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }


    private List<Telefone> getTelefones(FornecedorRequestDTO dto) {
        List<Telefone> telefones = new ArrayList<>();

        for (int i = 0; i < dto.telefones().size(); i++) {
            Telefone telefone = new Telefone();
            TelefoneRequestDTO telefoneRequestDTO = dto.telefones().get(i);
            telefone.setCodigoArea(telefoneRequestDTO.codigoArea());
            telefone.setNumero(telefoneRequestDTO.numero());
            telefones.add(telefone);
        }
        return telefones;
    }

}
