package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.Lote;
import br.unitins.tp1.placadevideo.resource.lote.LoteResource;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class LoteResourceTest {

    @Inject
    public LoteService loteService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/lotes")
                .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        LoteRequestDTO dto = new LoteRequestDTO(
                "L12345", // código do lote
                100, // quantidade
                LocalDate.of(2024, 10, 22) // data de fabricação
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/lotes")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "codigo", is("L12345"),
                        "quantidade", is(100),
                        "dataFabricacao", is("2024-10-22"));

        // Remover o lote inserido
        loteService.delete(loteService.findByCodigo("L12345").getId());
    }

    @Test
    public void testUpdateLote() {
        LoteRequestDTO dto = new LoteRequestDTO(
                "L12345",
                100,
                LocalDate.of(2024, 10, 22));

        long id = loteService.create(dto).getId();

        LoteRequestDTO novoDto = new LoteRequestDTO(
                "L67890",
                200,
                LocalDate.of(2025, 10, 22));

        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/lotes/" + id)
                .then()
                .statusCode(204);

        Lote lote = loteService.findById(id);

        assertEquals(lote.getCodigo(), "L67890");
        assertEquals(lote.getQuantidade(), Integer.valueOf(200));
        assertEquals(lote.getDataFabricacao(), LocalDate.of(2025, 10, 22));

        loteService.delete(lote.getId());
    }

    @Test
    public void testDeleteLote() {
        LoteRequestDTO dto = new LoteRequestDTO(
                "L12345",
                100,
                LocalDate.of(2024, 10, 22));

        long id = loteService.create(dto).getId();

        given()
                .when()
                .delete("/lotes/" + id)
                .then()
                .statusCode(204);

        Lote lote = loteService.findById(id);
        assertNull(lote);
    }

    @Test
    @TestHTTPEndpoint(LoteResource.class)
    public void testFindAll2() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1))
                .body("[1].codigo", is("L1002"));
    }

}
