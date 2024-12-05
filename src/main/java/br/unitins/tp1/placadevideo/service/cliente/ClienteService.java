package br.unitins.tp1.placadevideo.service.cliente;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.model.usuario.Cliente;

public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByNome(String nome);

    List<Cliente> findAll();

    Cliente findByMe(String username);

    Cliente findByUsername(String username);

    Cliente create(String username, ClienteRequestDTO dto);

    void addEndereco(Long clienteId, EnderecoRequestDTO dto);

    void addTelefone(Long clienteId, TelefoneClienteRequestDTO dto);

    void addCartao(Long clienteId, CartaoRequestDTO dto);

    Cliente update(Long idCliente, Long enderecoId, Long telefoneId, ClienteRequestDTO dto);

    void delete(Long id);

    void adicionarProdutoListaDesejo(String email, Long idProduto);

    void removerProdutoListaDesejo(String email, Long idProduto);

    List<PlacaDeVideo> getListaDesejos(String email);

}
