package br.unitins.tp1.placadevideo.service.telefone;



import br.unitins.tp1.placadevideo.dto.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.TelefoneFornecedor;

import java.util.List;

public interface TelefoneFornecedorService {

    TelefoneFornecedor findById(Long id);

    List<TelefoneFornecedor> findByFornecedor(Long id);

    List<TelefoneFornecedor> findAll();

    TelefoneFornecedor create(TelefoneFornecedorRequestDTO dto, Long id);

    TelefoneFornecedor update(Long id, TelefoneFornecedorRequestDTO dto);

    void delete(Long id);


}
