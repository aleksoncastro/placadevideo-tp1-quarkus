package br.unitins.tp1.placadevideo.service.fornecedor;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;

public interface FornecedorService {

    Fornecedor findById(Long id);

    Fornecedor findByCnpj(String cnpj);

    List<Fornecedor> findByNome(String nome);

    List<Fornecedor> findAll();

    Fornecedor create(FornecedorRequestDTO dto);

    void addTelefone(Long fornecedorId, TelefoneFornecedorRequestDTO dto);
    
    Fornecedor update(Long fornecedorId, Long telefoneId, FornecedorRequestDTO dto);

    void delete(Long id);


}
