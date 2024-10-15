package br.unitins.tp1.placadevideo.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;
import br.unitins.tp1.placadevideo.model.Endereco;
import br.unitins.tp1.placadevideo.repository.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public EnderecoRepository enderecoRepository;

    @Inject
    public EnderecoServiceImpl enderecoServiceImpl;

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cliente create(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setEmail(dto.email());

        // cria o cliente primeiro
        clienteRepository.persist(cliente);

        cliente.setEnderecos(new ArrayList<>());

        // endereco associado a ele
        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            Endereco endereco = enderecoServiceImpl.create(enderecoDTO, cliente.getId());
            cliente.getEnderecos().add(endereco);
        }
        // for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
        //     Endereco endereco = new Endereco();
        //     endereco.setCep(enderecoDTO.cep());
        //     endereco.setCidade(enderecoDTO.cidade());
        //     endereco.setEstado(enderecoDTO.estado());
        //     endereco.setBairro(enderecoDTO.bairro());
        //     endereco.setNumero(enderecoDTO.numero());
        //     endereco.setIdCliente(cliente.getId());

        //     enderecoRepository.persist(endereco);
        //     cliente.getEnderecos().add(endereco);
        // }
        //Atualiza o cliente no banco
        clienteRepository.persist(cliente);

        return cliente;
    }

    @Override
    @Transactional
    public void addEndereco(Long clienteId, EnderecoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente != null) {
            Endereco endereco = new Endereco();
            endereco.setCep(dto.cep());
            endereco.setCidade(dto.cidade());
            endereco.setEstado(dto.estado());
            endereco.setBairro(dto.bairro());
            endereco.setNumero(dto.numero());
            endereco.setIdCliente(clienteId);

            enderecoRepository.persist(endereco);
            cliente.getEnderecos().add(endereco);
        }
    }

    @Override
    @Transactional
    public Cliente update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        // Atualiza os dados do cliente
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setEmail(dto.email());

        // Atualiza os endereços do cliente
        // Limpa os endereços existentes
        cliente.getEnderecos().clear();

        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            enderecoServiceImpl.create(enderecoDTO, cliente.getId());
        }

        // Adiciona os novos endereços
        // for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
        //     Endereco endereco = new Endereco();
        //     endereco.setCep(enderecoDTO.cep());
        //     endereco.setCidade(enderecoDTO.cidade());
        //     endereco.setEstado(enderecoDTO.estado());
        //     endereco.setBairro(enderecoDTO.bairro());
        //     endereco.setNumero(enderecoDTO.numero());
        //     endereco.setIdCliente(cliente.getId());

        //     enderecoRepository.persist(endereco);
        //     cliente.getEnderecos().add(endereco);
        // }

        // Persistindo as alterações do cliente
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        enderecoRepository.deleteClienteEndereco(id);
        enderecoRepository.deleteByCliente(id);
        clienteRepository.deleteById(id);
    }

}
