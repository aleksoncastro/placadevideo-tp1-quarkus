package br.unitins.tp1.placadevideo.service.cliente;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.Request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    Cliente findByCpf(String cpf);

    List<Cliente> findAll();

    Cliente findByUsername(String username);

    Cliente create(ClienteRequestDTO dto);

    void addEndereco(Long clienteId, EnderecoRequestDTO dto);
    
    void addTelefone(Long clienteId, TelefoneClienteRequestDTO dto);

    void addCartao(Long clienteId, CartaoRequestDTO dto);

    Cliente update(Long idCliente, Long enderecoId, Long telefoneId, ClienteRequestDTO dto);

    void delete(Long id);


}
