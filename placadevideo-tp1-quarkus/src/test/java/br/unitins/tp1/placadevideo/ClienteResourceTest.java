package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;
import br.unitins.tp1.placadevideo.resource.cliente.ClienteResource;
import br.unitins.tp1.placadevideo.service.cliente.ClienteService;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneClienteService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteResourceTest {

    @Inject
    public ClienteService clienteService;

    @Inject
    public EnderecoService  enderecoService;

    @Inject
    public TelefoneClienteService  telefoneClienteService;



    @Test
    public void testFindAll() {
        given()
                .when().get("/pessoasfisicas")
                .then().statusCode(200);
    }

@Test
public void testCreate() {
    // Criando um objeto ClienteRequestDTO
    ClienteRequestDTO dto = new ClienteRequestDTO(
            "Carlos Henrique", // Nome do cliente
            "123.456.789-00", // CPF do cliente
            LocalDate.of(1990, 5, 15), // Data de nascimento
            "carlos@example.com", // Email do cliente
            List.of(new EnderecoRequestDTO(
                    "12345-678", // CEP
                    "São Paulo", // Cidade
                    "SP", // Estado
                    "Centro", // Bairro
                    "100" // Número
            )),
            List.of(new TelefoneClienteRequestDTO(
                    "11", // Código de área
                    "91234-5678" // Número de telefone
            ))
    );

    given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
            .post("/clientes") // Supondo que o endpoint seja /clientes
            .then()
            .statusCode(201)
            .body("id", notNullValue(),
                    "nome", is("Carlos Henrique"),
                    "cpf", is("123.456.789-00"),
                    "dataNascimento", is("1990-05-15"),
                    "email", is("carlos@example.com"),
                    "enderecos.cep", hasItem(is("12345-678")),
                    "telefones.numero", hasItem(is("91234-5678")));

    // Remover o cliente inserido
    clienteService.delete(clienteService.findByCpf("123.456.789-00").getId());
}

@Test
public void testUpdate() {
    // Criando um objeto ClienteRequestDTO inicial
    ClienteRequestDTO dto = new ClienteRequestDTO(
            "Carlos Henrique", // Nome do cliente
            "123.456.789-00", // CPF do cliente
            LocalDate.of(1990, 5, 15), // Data de nascimento
            "carlos@example.com", // Email do cliente
            List.of(new EnderecoRequestDTO(
                    "12345-678", // CEP
                    "São Paulo", // Cidade
                    "SP", // Estado
                    "Centro", // Bairro
                    "100" // Número
            )),
            List.of(new TelefoneClienteRequestDTO(
                    "11", // Código de área
                    "91234-5678" // Número de telefone
            ))
    );

    // Criar o cliente
    long id = clienteService.create(dto).getId();

    // Criando um novo objeto ClienteRequestDTO para atualização
    ClienteRequestDTO novoDto = new ClienteRequestDTO(
            "Leandra Cavina",
            "123.456.789-00", 
            LocalDate.of(1990, 5, 15),
            "leandra@example.com", 
            List.of(new EnderecoRequestDTO(
                    "98765-432", 
                    "Rio de Janeiro", 
                    "RJ", 
                    "Copacabana", 
                    "200" 
            )),
            List.of(new TelefoneClienteRequestDTO(
                    "21", 
                    "2222" 
            ))
    );

    
    // Adiciona o endereço e telefone para que possamos atualizá-los
    long enderecoId = enderecoService.findByCep("98765-432").getId();
    long telefoneId = 1l;

    // Atualizando o cliente, endereço e telefone
    given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
            .put("/clientes/" + id + "/endereco/" + enderecoId + "/telefone/" + telefoneId) // Usando o caminho especificado
            .then()
            .statusCode(204); // Espera-se um status 204 No Content

    // Verificando se os dados do cliente foram atualizados
    Cliente clienteAtualizado = clienteService.findById(id);

    assertEquals(clienteAtualizado.getNome(), "Leandra Cavina");
    assertEquals(clienteAtualizado.getEmail(), "leandra@example.com");

    // Remover o cliente inserido
    clienteService.delete(clienteAtualizado.getId());
}

@Test
    public void testDelete() {
        ClienteRequestDTO dto = new ClienteRequestDTO(
            "Carlos Henrique", // Nome do cliente
            "123.456.789-00", // CPF do cliente
            LocalDate.of(1990, 5, 15), // Data de nascimento
            "carlos@example.com", // Email do cliente
            List.of(new EnderecoRequestDTO(
                    "12345-678", // CEP
                    "São Paulo", // Cidade
                    "SP", // Estado
                    "Centro", // Bairro
                    "100" // Número
            )),
            List.of(new TelefoneClienteRequestDTO(
                    "11", // Código de área
                    "91234-5678" // Número de telefone
            ))
    );

        long id = clienteService.create(dto).getId();

        given()
            .when()
                .delete("/clientes/" + id)
            .then().statusCode(204);

        Cliente cliente = clienteService.findById(id);
        assertNull(cliente);
    }

    @Test
    @TestHTTPEndpoint(ClienteResource.class)
    public void testFindAll2() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1))
                .body("[1].nome", is("Maria Oliveira"));
    }


}
