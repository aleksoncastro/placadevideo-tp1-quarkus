package br.unitins.tp1.placadevideo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
//import br.unitins.tp1.placadevideo.dto.request.UsuarioRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import br.unitins.tp1.placadevideo.service.cliente.ClienteServiceImpl;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneClienteService;
import br.unitins.tp1.placadevideo.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteServiceTest {

    @Inject
    public ClienteServiceImpl clienteService;
    
    @Inject
    public EnderecoService enderecoService;

    @Inject
    public TelefoneClienteService telefoneClienteService;

    @Inject
    public UsuarioService usuarioService;

    private Usuario usuario;
    private Cliente cliente;

    @AfterEach
    public void cleanup() {
        // Limpeza após cada teste
        if (cliente != null && cliente.getId() != null) {
            clienteService.delete(cliente.getId());  // Deletando o cliente
        }
        if (usuario != null && usuario.getId() != null) {
            usuarioService.delete(usuario.getId());  // Deletando o usuário
        }
    }

    @Test
    public void testCreateCliente() {
        // Criando o usuário com o UsuarioRequestDTO através do método createCliente
        /*UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO(
            "usuarioTeste", 
            "senha123", 
            "usuario@teste.com", 
            "12345678900",
            1 // Supondo que o ID do perfil seja 1
        ); */
        
        // Criando o usuário usando o método createCliente
        //usuario = usuarioService.createCliente(usuarioDTO);

        // Criando um ClienteRequestDTO
        EnderecoRequestDTO enderecoDTO = new EnderecoRequestDTO("Rua X", "123", "Centro", "Cidade", "Estado", "12345-678");
        TelefoneClienteRequestDTO telefoneDTO = new TelefoneClienteRequestDTO("11", "123456789");

        ClienteRequestDTO dto = new ClienteRequestDTO(
            "Carlos Silva", 
            LocalDate.of(1990, 5, 20),
            List.of(enderecoDTO), 
            List.of(telefoneDTO)
        );

        // Chamada para criar o cliente, associando o usuário criado anteriormente
        cliente = clienteService.create(usuario.getUsername(), dto);

        // Verificando se a criação foi bem-sucedida
        assertNotNull(cliente.getId());
        assertEquals(cliente.getNome(), "Carlos Silva");
        assertEquals(cliente.getUsuario().getUsername(), "usuarioTeste");
    }



    

    @Test
    public void testAdicionarProdutoListaDesejo() {
        // Criando o usuário com o UsuarioRequestDTO através do método createCliente
        /*UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO(
            "usuarioTeste", 
            "senha123", 
            "usuario@teste.com", 
            "12345678900",
            1 // Supondo que o ID do perfil seja 1
        ); */
       // usuario = usuarioService.createCliente(usuarioDTO);

        // Criando um ClienteRequestDTO
        ClienteRequestDTO dto = new ClienteRequestDTO(
            "Carlos Silva", 
            LocalDate.of(1990, 5, 20),
            List.of(new EnderecoRequestDTO("Rua X", "123", "Centro", "Cidade", "Estado", "12345-678")),
            List.of(new TelefoneClienteRequestDTO("11", "123456789"))
        );

        cliente = clienteService.create(usuario.getUsername(), dto);
        
        // Adicionando produto à lista de desejos
        clienteService.adicionarProdutoListaDesejo(usuario.getUsername(), 1L);

        // Verificando se o produto foi adicionado
        List<PlacaDeVideo> listaDesejos = clienteService.getListaDesejos(usuario.getUsername());
        assertFalse(listaDesejos.isEmpty());
    }
}
