package br.unitins.tp1.placadevideo.service.telefone;



import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;

public interface TelefoneFornecedorService {

    TelefoneFornecedor findById(Long id);

    TelefoneFornecedor findByNumero(String numero);

    List<TelefoneFornecedor> findByFornecedor(Long id);

    List<TelefoneFornecedor> findAll();

    TelefoneFornecedor create(TelefoneFornecedorRequestDTO dto);

    TelefoneFornecedor update(Long id, TelefoneFornecedorRequestDTO dto);

    void delete(Long id);


}
