package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Endereco;
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
    // Criando um EnderecoRequestDTO
    EnderecoRequestDTO dto = new EnderecoRequestDTO(
        "12345-678",   // CEP
        "Cidade Teste", // Cidade
        "Estado Teste", // Estado
        "Bairro Teste", // Bairro
        "Rua Teste",    // Rua
        "123"           // Número
    );

    Long id = enderecoService.create(dto).getId();

    // Enviando uma requisição POST para criar o endereço
    given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/enderecos")
        .then()
            .statusCode(201)
            .body("id", notNullValue(), // Verificando se o ID foi gerado
                  "cep", is("12345-678"), // Verificando se o CEP foi corretamente atribuído
                  "cidade", is("Cidade Teste"), // Verificando se a cidade foi corretamente atribuída
                  "estado", is("Estado Teste"), // Verificando se o estado foi corretamente atribuído
                  "bairro", is("Bairro Teste"), // Verificando se o bairro foi corretamente atribuído
                  "rua", is("Rua Teste"), // Verificando se a rua foi corretamente atribuída
                  "numero", is("123")); // Verificando se o número foi corretamente atribuído

    // Removendo o dado que foi inserido
    enderecoService.delete(id);
}


    @Test
    public void testUpdate() {
        // Inserindo dado para alteração
        EnderecoRequestDTO dto = new EnderecoRequestDTO(
            "87654-321", "Rio de Janeiro", "RJ", "Copacabana", "Rua Y", "456");

        long id = enderecoService.create(dto).getId();

        EnderecoRequestDTO novoDto = new EnderecoRequestDTO(
            "87654-999", "São Paulo", "SP", "Centro", "Rua Z", "789");

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/enderecos/" + id)
            .then()
                .statusCode(204);

        Endereco endereco = enderecoService.findById(id);

        assertEquals(endereco.getCep(), "87654-999");
        assertEquals(endereco.getCidade(), "São Paulo");

        // Removendo o dado que foi inserido
        enderecoService.delete(endereco.getId());
    }

    @Test
    public void testDelete() {
        // Inserindo dado para exclusão
        EnderecoRequestDTO dto = new EnderecoRequestDTO(
            "00000-000", "Curitiba", "PR", "Batel", "Rua W", "100");

        long id = enderecoService.create(dto).getId();

        given()
            .when()
                .delete("/enderecos/" + id)
            .then()
                .statusCode(204);

     
    }

    
}
