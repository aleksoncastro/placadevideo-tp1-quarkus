package br.unitins.tp1.placadevideo.service.telefone;



import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;

import java.util.List;

public interface TelefoneClienteService {

    TelefoneCliente findById(Long id);

    TelefoneCliente findByNumero(String numero);

    TelefoneCliente findByCodigo(String codigo);

    List<TelefoneCliente> findByCliente(Long id);

    List<TelefoneCliente> findAll();

    TelefoneCliente create(TelefoneClienteRequestDTO dto);

    TelefoneCliente update(Long id, TelefoneClienteRequestDTO dto);

    void delete(Long id);


}
