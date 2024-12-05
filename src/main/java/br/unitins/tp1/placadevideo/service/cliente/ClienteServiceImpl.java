package br.unitins.tp1.placadevideo.service.cliente;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import br.unitins.tp1.placadevideo.model.usuario.Cartao;
import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import br.unitins.tp1.placadevideo.model.usuario.Endereco;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import br.unitins.tp1.placadevideo.repository.cartao.CartaoRepository;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.endereco.EnderecoRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneClienteRepository;
import br.unitins.tp1.placadevideo.repository.usuario.UsuarioRepository;
import br.unitins.tp1.placadevideo.service.cartao.CartaoService;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import br.unitins.tp1.placadevideo.service.hash.HashService;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
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

    @Inject
    public PlacaDeVideoService placaDeVideoService;

    @Override
    public Cliente findById(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
        return cliente;
    }
    @Override
    public Cliente findByMe(String username) {
        return clienteRepository.findByUsername(username);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("nome", "O nome não pode estar vazio.");
        }
        return clienteRepository.findByNome(nome);
    }

    @Override
    public Cliente findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("username", "O username não pode estar vazio.");
        }
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado com o username informado.");
        }
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cliente create(String username, ClienteRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("clienteRequestDTO", "O objeto DTO não pode ser nulo.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("username", "O username não pode estar vazio.");
        }

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new ValidationException("username", "Usuário não encontrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setUsuario(usuario);

        clienteRepository.persist(cliente);
        cliente.setEnderecos(new ArrayList<>());
        cliente.setTelefones(new ArrayList<>());

        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            Endereco endereco = enderecoService.create(enderecoDTO);
            cliente.getEnderecos().add(endereco);
        }

        for (TelefoneClienteRequestDTO telefoneDTO : dto.telefones()) {
            TelefoneCliente telefone = telefoneClienteService.create(telefoneDTO);
            cliente.getTelefones().add(telefone);
        }

        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public void addEndereco(Long clienteId, EnderecoRequestDTO dto) {
        Cliente cliente = findById(clienteId);
        Endereco endereco = enderecoService.create(dto);
        cliente.getEnderecos().add(endereco);
    }

    @Override
    @Transactional
    public void addTelefone(Long clienteId, TelefoneClienteRequestDTO dto) {
        Cliente cliente = findById(clienteId);
        TelefoneCliente telefone = telefoneClienteService.create(dto);
        cliente.getTelefones().add(telefone);
    }

    @Override
    @Transactional
    public void addCartao(@Valid Long clienteId, CartaoRequestDTO dto) {
        Cliente cliente = findById(clienteId);
        Cartao cartao = cartaoService.create(dto);
        cliente.getCartoes().add(cartao);
    }

    @Override
    @Transactional
    public Cliente update(Long idCliente, Long enderecoId, Long telefoneId, ClienteRequestDTO dto) {
        Cliente cliente = findById(idCliente);
        cliente.setNome(dto.nome());
        cliente.setDataNascimento(dto.dataNascimento());

        for (EnderecoRequestDTO enderecoDTO : dto.enderecos()) {
            enderecoService.update(enderecoId, enderecoDTO);
        }

        for (TelefoneClienteRequestDTO telefoneDTO : dto.telefones()) {
            telefoneClienteService.update(telefoneId, telefoneDTO);
        }

        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("id", "O ID deve ser um valor positivo e válido.");
        }
        if (!clienteRepository.deleteById(id)) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @Override
    @Transactional
    public void adicionarProdutoListaDesejo(String username, Long idProduto) {
        Cliente cliente = findByUsername(username);
        if (cliente.getListaDesejos() == null) {
            cliente.setListaDesejos(new ArrayList<>());
        }
        PlacaDeVideo placadevideo = placaDeVideoService.findById(idProduto);
        if (placadevideo == null) {
            throw new ValidationException("idProduto", "Placa de vídeo não encontrada.");
        }
        cliente.getListaDesejos().add(placadevideo);
    }

    @Override
    @Transactional
    public void removerProdutoListaDesejo(String username, Long idProduto) {
        Cliente cliente = findByUsername(username);
        List<PlacaDeVideo> listaDesejos = cliente.getListaDesejos();
        if (listaDesejos == null || listaDesejos.isEmpty()) {
            throw new ValidationException("listaDesejos", "Você não possui uma lista de desejos.");
        }

        PlacaDeVideo placadevideo = placaDeVideoService.findById(idProduto);
        if (placadevideo == null || !listaDesejos.contains(placadevideo)) {
            throw new ValidationException("idProduto", "O produto não está na lista de desejos.");
        }
        listaDesejos.remove(placadevideo);
    }

    @Override
    public List<PlacaDeVideo> getListaDesejos(String username) {
        Cliente cliente = findByUsername(username);
        return cliente.getListaDesejos();
    }
}
