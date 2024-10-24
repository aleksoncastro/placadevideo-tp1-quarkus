package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.Endereco;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EnderecoResourceTest {

    @Inject
    public EnderecoService enderecoService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/enderecos")
                .then().statusCode(200);
    }

    @Test
    public void testCreateEndereco() {
        long idCliente = 1L; // ID fictício do cliente

        EnderecoRequestDTO dto = new EnderecoRequestDTO(
                "12345-678", // CEP
                "São Paulo", // Cidade
                "SP", // Estado
                "Centro", // Bairro
                "123" // Número
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/enderecos/" + idCliente)
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "cep", is("12345-678"),
                        "cidade", is("São Paulo"),
                        "estado", is("SP"),
                        "bairro", is("Centro"),
                        "numero", is("123"));

        // Remover o endereço inserido
        enderecoService.delete(enderecoService.findByCep("12345-678").getId());
    }

    @Test
    public void testUpdateEndereco() {
        long idEndereco = 1L; // ID fictício do endereço

        EnderecoRequestDTO dto = new EnderecoRequestDTO(
                "12345-678", // CEP
                "São Paulo", // Cidade
                "SP", // Estado
                "Centro", // Bairro
                "123" // Número
        );

        // Criar o endereço antes de testar a atualização
        enderecoService.create(dto);

        EnderecoRequestDTO novoDto = new EnderecoRequestDTO(
                "98765-432", // Novo CEP
                "Rio de Janeiro", // Nova Cidade
                "RJ", // Novo Estado
                "Copacabana", // Novo Bairro
                "456" // Novo Número
        );

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/enderecos/" + idEndereco)
                .then()
                .statusCode(204);

        Endereco endereco = enderecoService.findById(idEndereco);

        assertEquals(endereco.getCep(), "98765-432");
        assertEquals(endereco.getCidade(), "Rio de Janeiro");
        assertEquals(endereco.getEstado(), "RJ");
        assertEquals(endereco.getBairro(), "Copacabana");
        assertEquals(endereco.getNumero(), "456");

        // Remover o endereço inserido
        enderecoService.delete(endereco.getId());
    }

    @Test
    public void testDelete() {
        EnderecoRequestDTO dto = new EnderecoRequestDTO(
                "12345-678", // CEP
                "São Paulo", // Cidade
                "SP", // Estado
                "Centro", // Bairro
                "123" // Número
        );

        long id = enderecoService.create(dto).getId();

        given()
                .when()
                .delete("/enderecos/" + id)
                .then()
                .statusCode(204);

        Endereco endereco = enderecoService.findById(id);
        assertNull(endereco);
    }


}
