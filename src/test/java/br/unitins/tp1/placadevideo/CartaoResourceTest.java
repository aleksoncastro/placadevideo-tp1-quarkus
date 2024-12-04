package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Cartao;
import br.unitins.tp1.placadevideo.service.cartao.CartaoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class CartaoResourceTest {

    @Inject
    public CartaoService cartaoService;

    @Test
    public void testFindAll() {
        given()
            .when().get("/cartoes")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        CartaoRequestDTO dto = new CartaoRequestDTO(
            "1234567890123456", 
            "Carlos Henrique", 
            LocalDate.of(2025, 12, 31), 
            "123", 
            "12345678901"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/cartoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "numero", is("1234567890123456"),
                      "titular", is("Carlos Henrique"));

        // Removendo o dado inserido
        Cartao cartao = cartaoService.findById(cartaoService.findByTitular("Carlos Henrique").get(0).getId());
        cartaoService.delete(cartao.getId());
    }

    @Test
    public void testUpdate() {
        // Inserindo dado para alteração
        CartaoRequestDTO dto = new CartaoRequestDTO(
            "1234567890123456", 
            "Carlos Henrique", 
            LocalDate.of(2025, 12, 31), 
            "123", 
            "12345678901"
        );
        
        long id = cartaoService.create(dto).getId();

        CartaoRequestDTO novoDto = new CartaoRequestDTO(
            "6543210987654321", 
            "Leandra Cavina", 
            LocalDate.of(2026, 6, 30), 
            "321", 
            "98765432100"
        );

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/cartoes/" + id)
            .then()
                .statusCode(204);

        Cartao cartao = cartaoService.findById(id);
        assertEquals(cartao.getNumero(), "6543210987654321");
        assertEquals(cartao.getTitular(), "Leandra Cavina");

        // Removendo o dado inserido
        cartaoService.delete(cartao.getId());
    }

    @Test
    public void testDelete() {
        // Inserindo dado para deleção
        CartaoRequestDTO dto = new CartaoRequestDTO(
            "1234567890123456", 
            "Carlos Henrique", 
            LocalDate.of(2025, 12, 31), 
            "123", 
            "12345678901"
        );
        
        long id = cartaoService.create(dto).getId();

        given()
            .when()
                .delete("/cartoes/" + id)
            .then()
                .statusCode(204);

        
    }

    @Test
    public void testFindByTitular() {
        // Inserindo dado para teste de busca
        CartaoRequestDTO dto = new CartaoRequestDTO(
            "1234567890123456", 
            "Carlos Henrique", 
            LocalDate.of(2025, 12, 31), 
            "123", 
            "12345678901"
        );
        
        cartaoService.create(dto);

        given()
            .when()
                .get("/cartoes?titular=Carlos Henrique")
            .then()
                .statusCode(200)
                .body("[0].titular", is("Carlos Henrique"));

        // Removendo o dado inserido
        Cartao cartao = cartaoService.findByTitular("Carlos Henrique").get(0);
        cartaoService.delete(cartao.getId());
    }
}
