package br.unitins.tp1.placadevideo.service.telefone;



import br.unitins.tp1.placadevideo.dto.request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;

import java.util.List;

public interface TelefoneFuncionarioService {

    TelefoneFuncionario findById(Long id);

    TelefoneFuncionario findByNumero(String numero);

    TelefoneFuncionario findByCodigo(String codigo);

    List<TelefoneFuncionario> findByFuncionario(Long id);

    List<TelefoneFuncionario> findAll();

    TelefoneFuncionario create(TelefoneFuncionarioRequestDTO dto);

    TelefoneFuncionario update(Long id, TelefoneFuncionarioRequestDTO dto);

    void delete(Long id);


}
