package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.MunicipioRequestDTO;
import br.unitins.tp1.placadevideo.model.Municipio;
import br.unitins.tp1.placadevideo.resource.municipio.MunicipioResource;
import br.unitins.tp1.placadevideo.service.municipio.MunicipioService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MunicipioResourceTest {
    
    @Inject
    public MunicipioService municipioService;

    @Test
    public void testFindById() {
        given()
            .when().get("/municipios/1")
            .then().statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome() {
        given()
            .when().pathParam("nome", "São Paulo")
            .get("/municipios/search/{nome}")
            .then().statusCode(200)
            .body("nome", hasItem(is("São Paulo")));
    }

    @Test
    public void testFindAll() {
        given()
            .when().get("/municipios")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Campo Grande", 1l);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/municipios")
            .then()
                .statusCode(201)
                .body("nome", is("Campo Grande"),
                      "estado.nome", is("São Paulo"),
                      "estado.sigla", is("SP"));
                
        // removendo o dado que foi inserido
        municipioService.delete(municipioService.findByNome("Campo Grande").getFirst().getId());
    }

    @Test
    public void testUpdate() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Campo Grande", 1l);
        long id = municipioService.create(dto).getId();

        MunicipioRequestDTO novoDto = new MunicipioRequestDTO("Campo Grande", 1l);

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/municipios/" + id)
            .then()
                .statusCode(204);
        
        Municipio municipio = municipioService.findById(id);

        assertEquals(municipio.getNome(), "Campo Grande");

        municipioService.delete(id);

    }  

    @Test
    public void testDelete() {
        MunicipioRequestDTO dto = new MunicipioRequestDTO("Campo Grande", 1l);
        Long id = municipioService.create(dto).getId();
    
        given()
            .when()
                .delete("/municipios/" + id)
            .then().statusCode(204);

        Municipio municipio = municipioService.findById(id);
        assertNull(municipio);

    }

    @Test
    @TestHTTPEndpoint(MunicipioResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1));
    }
    
}