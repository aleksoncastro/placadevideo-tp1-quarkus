package br.unitins.tp1.placadevideo.service.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import br.unitins.tp1.placadevideo.dto.Request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import br.unitins.tp1.placadevideo.model.usuario.Cartao;
import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import br.unitins.tp1.placadevideo.model.usuario.Endereco;
import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.endereco.EnderecoRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneClienteRepository;
import br.unitins.tp1.placadevideo.repository.usuario.UsuarioRepository;
import br.unitins.tp1.placadevideo.service.cartao.CartaoService;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import br.unitins.tp1.placadevideo.service.hash.HashService;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneClienteService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public EnderecoRepository enderecoRepository;

    @Inject
    public EnderecoService enderecoService;

    @Inject
    public TelefoneClienteRepository telefoneClienteRepository;

    @Inject
    public TelefoneClienteService telefoneClienteService;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Inject 
    public CartaoService cartaoService;

    @Inject
    public CartaoRepository cartaoRepository;


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
    public Cliente findByUsername(String username) {
        return clienteRepository.findByUsername(username);
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

        // Criando um Usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.usuario().username());
        usuario.setSenha(hashService.getHashSenha(dto.usuario().senha()));
        usuario.setPerfil(Perfil.USER);
        usuarioRepository.persist(usuario);

        cliente.setUsuario(usuario);

        // cria o cliente primeiro
        clienteRepository.persist(cliente);

        cliente.setEnderecos(new ArrayList<>());
        cliente.setTelefones(new ArrayList<>());

        // endereco e telefone associado a ele
        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            Endereco endereco = enderecoService.create(enderecoDTO);
            cliente.getEnderecos().add(endereco);
        }

        for (TelefoneClienteRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneCliente telefone = telefoneClienteService.create(telefoneDTO);
            cliente.getTelefones().add(telefone);
        }

        // Atualiza o cliente no banco
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

        Endereco endereco = enderecoService.create(dto);
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

        TelefoneCliente telefone = telefoneClienteService.create(dto);
        // telefone.setCliente(cliente);
        telefoneClienteRepository.persist(telefone);
        cliente.getTelefones().add(telefone);

    }

    @Override
    @Transactional
    public void addCartao(@Valid Long clienteId, CartaoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new ValidationException("idCliente", "idCliente não encontrado");
        }

        Cartao cartao = cartaoService.create(dto);
        cartaoRepository.persist(cartao);
        cliente.getCartoes().add(cartao);
    }

    @Override
    @Transactional
    public Cliente update(Long idCliente, Long enderecoId, Long telefoneId, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        // Atualiza os dados do cliente
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setEmail(dto.email());

        // Criando um Usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.usuario().username());
        usuario.setSenha(hashService.getHashSenha(dto.usuario().senha()));
        usuario.setPerfil(Perfil.USER);
        usuarioRepository.persist(usuario);

        cliente.setUsuario(usuario);

        // Atualiza os endereços do cliente
        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            Endereco enderecoExistente = enderecoRepository.findById(enderecoId);
            if (enderecoExistente != null) {
                enderecoService.update(enderecoExistente.getId(), enderecoDTO);
            } else {
                throw new NoSuchElementException("Não encontrado!");
            }
        }

        // Atualiza os telefones do cliente
        for (TelefoneClienteRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneCliente telefoneExistente = telefoneClienteRepository.findById(telefoneId);
            if (telefoneExistente != null) {
                telefoneClienteService.update(telefoneExistente.getId(), telefoneDTO);
            } else {
                throw new NoSuchElementException("Não encontrado!");
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
