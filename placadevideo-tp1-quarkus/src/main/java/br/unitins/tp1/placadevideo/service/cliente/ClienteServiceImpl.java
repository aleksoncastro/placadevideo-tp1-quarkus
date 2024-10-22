package br.unitins.tp1.placadevideo.service.cliente;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;
import br.unitins.tp1.placadevideo.model.Endereco;
import br.unitins.tp1.placadevideo.model.TelefoneCliente;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.endereco.EnderecoRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneClienteRepository;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoServiceImpl;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneClienteServiceImpl;
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

    @Inject
    public  TelefoneClienteRepository  telefoneClienteRepository;

    @Inject
    public TelefoneClienteServiceImpl  telefoneClienteServiceImpl;


    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

     @Override
    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
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
        cliente.setTelefones(new ArrayList<>());

        // endereco e telefone associado a ele
        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            Endereco endereco = enderecoServiceImpl.create(enderecoDTO);
            cliente.getEnderecos().add(endereco);
        }

        for (TelefoneClienteRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneCliente telefone = telefoneClienteServiceImpl.create(telefoneDTO);
            cliente.getTelefones().add(telefone);
        }
        
        //Atualiza o cliente no banco
        clienteRepository.persist(cliente);

        return cliente;
    }

    @Override
    @Transactional
    public void addEndereco(Long clienteId, EnderecoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente com ID " + clienteId + " não encontrado.");
        }
    
        Endereco endereco = enderecoServiceImpl.create(dto);
        endereco.setCliente(cliente);
        enderecoRepository.persist(endereco);
        cliente.getEnderecos().add(endereco);
    }

    @Override
    @Transactional
    public void addTelefone(Long clienteId, TelefoneClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente com ID " + clienteId + " não encontrado.");
        }

        TelefoneCliente telefone = telefoneClienteServiceImpl.create(dto);
        telefone.setCliente(cliente);
        telefoneClienteRepository.persist(telefone);
        cliente.getTelefones().add(telefone);

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
    for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
       
        Endereco enderecoExistente = cliente.getEnderecos().stream().filter(e -> e.getId()); 
        if (enderecoExistente != null) {
            enderecoServiceImpl.update(enderecoExistente.getId(), enderecoDTO);
        } else {
            Endereco novoEndereco = enderecoServiceImpl.create(enderecoDTO);
            cliente.getEnderecos().add(novoEndereco);
        }
    }

    // Atualiza os telefones do cliente
    for (TelefoneClienteRequestDTO telefoneDTO : dto.telefones()) {
        TelefoneCliente telefoneExistente = telefoneClienteServiceImpl.findByNumero(telefoneDTO.numero()); // Assumindo que TelefoneCliente tem um método id()

        if (telefoneExistente != null) {
            telefoneClienteServiceImpl.update(telefoneExistente.getId(), telefoneDTO);
        } else {
            TelefoneCliente novoTelefone = telefoneClienteServiceImpl.create(telefoneDTO);
            cliente.getTelefones().add(novoTelefone);
        }
    }

        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

}
