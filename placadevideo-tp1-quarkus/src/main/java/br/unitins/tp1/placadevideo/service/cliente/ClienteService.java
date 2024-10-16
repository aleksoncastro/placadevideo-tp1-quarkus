package br.unitins.tp1.placadevideo.service.cliente;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    List<Cliente> findAll();

    Cliente create(ClienteRequestDTO dto);

    void addEndereco(Long clienteId, EnderecoRequestDTO dto);

    Cliente update(Long id, ClienteRequestDTO dto);

    void delete(Long id);


}
