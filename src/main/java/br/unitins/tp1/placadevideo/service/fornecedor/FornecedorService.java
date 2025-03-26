package br.unitins.tp1.placadevideo.service.fornecedor;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;

public interface FornecedorService {

    Fornecedor findById(Long id);

    Fornecedor findByCnpj(String cnpj);

    Fornecedor findByIdComTelefones(Long id);

    List<Fornecedor> findByNome(String nome, Integer page, Integer pageSize);

    List<Fornecedor> findAll(Integer page, Integer pageSize);

    Fornecedor create(FornecedorRequestDTO dto);

    void addTelefone(Long fornecedorId, TelefoneFornecedorRequestDTO dto);
    
    Fornecedor update(Long fornecedorId, Long telefoneId, FornecedorRequestDTO dto);

    void delete(Long id);

    long count();

    long count(String nome);


}
