package br.unitins.tp1.placadevideo.service.telefone;



import br.unitins.tp1.placadevideo.dto.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.TelefoneCliente;

import java.util.List;

public interface TelefoneClienteService {

    TelefoneCliente findById(Long id);

    TelefoneCliente findByNumero(String numero);

    List<TelefoneCliente> findByCliente(Long id);

    List<TelefoneCliente> findAll();

    TelefoneCliente create(TelefoneClienteRequestDTO dto);

    TelefoneCliente update(Long id, TelefoneClienteRequestDTO dto);

    void delete(Long id);


}
