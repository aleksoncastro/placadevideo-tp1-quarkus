package br.unitins.tp1.placadevideo.service.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.TelefoneFornecedor;
import br.unitins.tp1.placadevideo.repository.fornecedor.FornecedorRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneFornecedorServiceImpl implements TelefoneFornecedorService {

    @Inject
    public TelefoneFornecedorRepository telefonefornecedorRepository;

    @Inject
    public FornecedorRepository  fornecedorRepository;


    @Override
    public TelefoneFornecedor findById(Long id) {
        return telefonefornecedorRepository.findById(id);
    }

    @Override
    public List<TelefoneFornecedor> findByFornecedor(Long id) {
        return telefonefornecedorRepository.findByTelefoneFornecedor(id);
    }

    @Override
    public List<TelefoneFornecedor> findAll() {
        return telefonefornecedorRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneFornecedor create(TelefoneFornecedorRequestDTO dto, Long id){

        TelefoneFornecedor telefone = new TelefoneFornecedor();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefonefornecedorRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public TelefoneFornecedor update(Long id, TelefoneFornecedorRequestDTO dto) {
        TelefoneFornecedor telefone = telefonefornecedorRepository.findById(id);
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefonefornecedorRepository.persist(telefone);

        return telefone;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefonefornecedorRepository.deleteById(id);
    }

}
