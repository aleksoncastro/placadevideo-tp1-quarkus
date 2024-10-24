package br.unitins.tp1.placadevideo.service.cliente;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    Cliente findByCpf(String cpf);

    List<Cliente> findAll();

    Cliente create(ClienteRequestDTO dto);

    void addEndereco(Long clienteId, EnderecoRequestDTO dto);
    
    void addTelefone(Long clienteId, TelefoneClienteRequestDTO dto);

    Cliente update(Long idCliente, Long enderecoId, Long telefoneId, ClienteRequestDTO dto);

    void delete(Long id);


}
