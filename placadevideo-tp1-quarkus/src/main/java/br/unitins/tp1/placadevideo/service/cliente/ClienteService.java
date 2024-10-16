package br.unitins.tp1.placadevideo.service.cliente;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    List<Cliente> findAll();

    Cliente create(ClienteRequestDTO dto);

    Cliente update(Long id, ClienteRequestDTO dto);

    void delete(Long id);


}
