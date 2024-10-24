package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.model.Cartao;
import br.unitins.tp1.placadevideo.resource.cartao.CartaoResource;
import br.unitins.tp1.placadevideo.service.cartao.CartaoService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
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
    public void testCreateC() {
        CartaoRequestDTO dto = new CartaoRequestDTO(
                "123", // número do cartão
                "Carlos Henrique", // titular do cartão
                LocalDate.of(2025, 10, 22), // data de validade
                "123" // CVV
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/cartoes")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "numero", is("123"),
                        "titular", is("Carlos Henrique"),
                        "dataValidade", is("2025-10-22"),
                        "cvv", is("123"));

        // Remover o cartão inserido
        cartaoService.delete(cartaoService.findByTitular("Carlos Henrique").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        CartaoRequestDTO dto = new CartaoRequestDTO(
                "1234",
                "Carlos Henrique",
                LocalDate.of(2025, 10, 22),
                "123");

        long id = cartaoService.create(dto).getId();

        CartaoRequestDTO novoDto = new CartaoRequestDTO(
                "5678",
                "Leandra Cavina",
                LocalDate.of(2026, 10, 22),
                "321");

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/cartoes/" + id)
                .then()
                .statusCode(204);

        Cartao cartao = cartaoService.findById(id);

        assertEquals(cartao.getNumero(), "5678");
        assertEquals(cartao.getTitular(), "Leandra Cavina");
        assertEquals(cartao.getDataValidade(), LocalDate.of(2026, 10, 22));
        assertEquals(cartao.getCvv(), "321");

        cartaoService.delete(cartao.getId());
    }

    @Test
    public void testDelete() {
        CartaoRequestDTO dto = new CartaoRequestDTO(
                "1234567812345678",
                "Carlos Henrique",
                LocalDate.of(2025, 10, 22),
                "123");

        long id = cartaoService.create(dto).getId();

        given()
                .when()
                .delete("/cartoes/" + id)
                .then()
                .statusCode(204);

        Cartao cartao = cartaoService.findById(id);
        assertNull(cartao);
    }

    @Test
    @TestHTTPEndpoint(CartaoResource.class)
    public void testFindAll2() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1))
                .body("[1].titular", is("Maria Oliveira"));
    }

}
