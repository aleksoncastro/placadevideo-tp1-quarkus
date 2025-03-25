package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class EstadoResourceTest {
    
    @Inject
    public EstadoService estadoService;

    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImFkbWluX3VzZXIiLCJncm91cHMiOlsiQWRtIl0sImV4cCI6MTczMzQyNTE4MiwiaWF0IjoxNzMzMzM4NzgyLCJqdGkiOiI4YWExZjE5OC04ZGU1LTRiYzUtOTdkNi05NmVlNGY1YmE2MjYifQ.oyMawfzv7FgXztSGjzFcTuYSgDz82pqH1_tVMZ5xvedEwewLvPtNbxjKz4jo91m2PO0GJHpguSdhfdCLTACa_MKlZvZdwugeN2mowxLhyMi1Mg2md-ZMS3GY8A6q9n_0q_-7xvtCwoumPg4CGL63eTOXStY2BX0jh3ieESVdRJK7VQXGkUjDjoqYlvpEePnmnHK-pt2sBMR4PrLyz13BqOBsvT9vJTahUsmgTdSuUbKa8iZyD9ZzIUli75H8RzCmQviYjAMrOlFiBxLpUXekkqYzJ7PyswaQ1y3C09aALO_5777wlpGAwz-AM7I9-XUgTGmtcjqt4e2-vsB7eDT7Yw";

    @Test
public void testFindById() {
    given()
        .header("Authorization", "Bearer "+ token)
        .when().get("/estados/1")
        .then().statusCode(200)
        .body("id", is(1));
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
       // estadoService.delete(estadoService.findByNome("Tocantins").getFirst().getId());
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

   
}