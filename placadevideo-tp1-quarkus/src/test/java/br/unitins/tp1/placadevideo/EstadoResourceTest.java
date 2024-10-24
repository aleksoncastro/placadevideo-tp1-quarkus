package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.resource.estado.EstadoResource;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class EstadoResourceTest {
    
    @Inject
    public EstadoService estadoService;

    @Test
    public void testFindById() {
        given()
            .when().get("/estados/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
            .when().pathParam("nome", "São Paulo")
            .get("/estados/search/{nome}")
            .then().statusCode(200)
            .body("nome", hasItem(is("São Paulo")));
    }

    @Test
    public void testFindAll() {
        given()
            .when().get("/estados")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        EstadoRequestDTO dto = new EstadoRequestDTO("Tocantins", "TO");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
                .post("/estados")
            .then()
                .statusCode(200)
                .body("nome", is("Tocantins"),
                      "sigla", is("TO"));
                
        // removendo o dado que foi inserido
        estadoService.delete(estadoService.findByNome("Tocantins").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        EstadoRequestDTO dto = new EstadoRequestDTO("Tocantins", "TO");

        long id = estadoService.create(dto).getId();

        EstadoRequestDTO novoDto = new EstadoRequestDTO("Bahia", "BA");

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/estados/" + id)
            .then()
                .statusCode(204);
        
        Estado estado = estadoService.findById(id);

        assertEquals(estado.getNome(), "Bahia");
        assertEquals(estado.getSigla(), "BA");

        estadoService.delete(id);

    }  

    @Test
    public void testDelete() {
        EstadoRequestDTO dto = new EstadoRequestDTO("Tocantins", "TO");

        Long id = estadoService.create(dto).getId();

        given()
            .when()
                .delete("/estados/" + id)
            .then().statusCode(204);

        Estado estado = estadoService.findById(id);
        assertNull(estado);

    }

    @Test
    @TestHTTPEndpoint(EstadoResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1));
    }
    
}