package br.unitins.tp1.placadevideo.service.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;
import br.unitins.tp1.placadevideo.repository.fornecedor.FornecedorRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneFornecedorServiceImpl implements TelefoneFornecedorService {

    @Inject
    public TelefoneFornecedorRepository telefoneFornecedorRepository;

    @Inject
    public FornecedorRepository  fornecedorRepository;


    @Override
    public TelefoneFornecedor findById(Long id) {
        return telefoneFornecedorRepository.findById(id);
    }

    @Override
    public TelefoneFornecedor findByNumero(String numero) {
        return telefoneFornecedorRepository.findByNumero(numero); 
    }

    @Override
    public List<TelefoneFornecedor> findByFornecedor(Long id) {
        return telefoneFornecedorRepository.findByTelefoneFornecedor(id);
    }

    @Override
    public List<TelefoneFornecedor> findAll() {
        return telefoneFornecedorRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneFornecedor create(TelefoneFornecedorRequestDTO dto){

        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneFornecedorRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public TelefoneFornecedor update(Long id, TelefoneFornecedorRequestDTO dto) {
        TelefoneFornecedor telefone = telefoneFornecedorRepository.findById(id);
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneFornecedorRepository.persist(telefone);

        return telefone;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneFornecedorRepository.deleteById(id);
    }

}
