package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;
import br.unitins.tp1.placadevideo.service.cliente.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteResourceTest {

    @Inject
    public ClienteService clienteService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/pessoasfisicas")
                .then().statusCode(200);
    }

    @Test
    public void testCreate() {

        EnderecoRequestDTO endereco = new EnderecoRequestDTO(
                "12345-678",
                "Cidade Exemplo",
                "Estado Exemplo",
                "Bairro Exemplo",
                "123");

        TelefoneClienteRequestDTO telefone = new TelefoneClienteRequestDTO(
                "11",
                "987654321");

        List<EnderecoRequestDTO> enderecos = Collections.singletonList(endereco);
        List<TelefoneClienteRequestDTO> telefones = Collections.singletonList(telefone);

        ClienteRequestDTO dto = new ClienteRequestDTO(
                "Alek Castro",
                "111",
                LocalDate.of(2000, 1, 1),
                "alek@gmail.com",
                enderecos,
                telefones);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/clientes")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Alek Castro"),
                        "cpf", is("111"),
                        "dataNascimento", is("2000-01-01"),
                        "enderecos[0].cep", is("12345-678"),
                        "enderecos[0].cidade", is("Cidade Exemplo"),
                        "enderecos[0].estado", is("Estado Exemplo"),
                        "enderecos[0].bairro", is("Bairro Exemplo"),
                        "enderecos[0].numero", is("123"),
                        "telefones[0].codigoArea", is("11"),
                        "telefones[0].numero", is("987654321"));

        clienteService.delete(clienteService.findByCpf("111").getId());

    }

    @Test
public void testUpdate() {
    // Criar o primeiro EnderecoRequestDTO
    EnderecoRequestDTO endereco = new EnderecoRequestDTO(
            "12345-678",
            "Cidade Exemplo",
            "Estado Exemplo",
            "Bairro Exemplo",
            "123"
    );

    // Criar o primeiro TelefoneClienteRequestDTO
    TelefoneClienteRequestDTO telefone = new TelefoneClienteRequestDTO(
            "11",
            "987654321"
    );

    // Criar a lista de endereços e telefones
    List<EnderecoRequestDTO> enderecos = Collections.singletonList(endereco);
    List<TelefoneClienteRequestDTO> telefones = Collections.singletonList(telefone);

    // Criar o ClienteRequestDTO inicial
    ClienteRequestDTO dto = new ClienteRequestDTO(
            "Alek Castro",
            "111",
            LocalDate.of(2000, 1, 1),
            "alek@gmail.com",
            enderecos,
            telefones
    );

    // Criar o cliente e obter o ID
    long id = clienteService.create(dto).getId();

    // Criar o novo EnderecoRequestDTO e TelefoneClienteRequestDTO para a atualização
    EnderecoRequestDTO novoEndereco = new EnderecoRequestDTO(
            "67890-123",
            "Outra Cidade",
            "Outro Estado",
            "Outro Bairro",
            "456"
    );

    TelefoneClienteRequestDTO novoTelefone = new TelefoneClienteRequestDTO(
            "21",
            "912345678"
    );

    // Criar a lista de novos endereços e telefones
    List<EnderecoRequestDTO> novosEnderecos = Collections.singletonList(novoEndereco);
    List<TelefoneClienteRequestDTO> novosTelefones = Collections.singletonList(novoTelefone);

    // Criar o ClienteRequestDTO atualizado
    ClienteRequestDTO novoDto = new ClienteRequestDTO(
            "Irlanderson Pinto",
            "222",
            LocalDate.of(2077, 9, 11),
            "irlanderson@gmail.com",
            novosEnderecos,
            novosTelefones
    );

    // Realizar a requisição PUT para atualizar o cliente
    given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
            .put("/clientes/" + id)
            .then()
            .statusCode(204); // Espera-se um status 204 (No Content)

    // Recuperar o cliente atualizado
    Cliente c = clienteService.findById(id);

    // Asserts para verificar os campos do cliente atualizado
    assertEquals("Irlanderson Pinto", c.getNome());
    assertEquals("222", c.getCpf());
    assertEquals(LocalDate.of(2077, 9, 11), c.getDataNascimento());
    assertEquals("irlanderson@gmail.com", c.getEmail());

    /*  Verificar endereços
    assertEquals(1, c.getEnderecos().size(), "Deveria haver 1 endereço");
    assertEquals("67890-123", c.getEnderecos().get(1).getCep());
    assertEquals("Outra Cidade", c.getEnderecos().get(1).getCidade());
    assertEquals("Outro Estado", c.getEnderecos().get(1).getEstado());
    assertEquals("Outro Bairro", c.getEnderecos().get(1).getBairro());
    assertEquals("456", c.getEnderecos().get(1).getNumero());

    // Verificar telefones
    assertEquals(1, c.getTelefones().size(), "Deveria haver 1 telefone");
    assertEquals("21", c.getTelefones().get(1).getCodigoArea());
    assertEquals("912345678", c.getTelefones().get(1).getNumero());
    */

    // Deletar o cliente após o teste
    clienteService.delete(clienteService.findByCpf("222").getId());
}


}
