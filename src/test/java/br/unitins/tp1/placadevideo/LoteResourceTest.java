package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class LoteResourceTest {

    @Inject
    public LoteService loteService;

    // Definindo o token de autorização
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImFkbWluX3VzZXIiLCJncm91cHMiOlsiQWRtIl0sImV4cCI6MTczMzQyNTE4MiwiaWF0IjoxNzMzMzM4NzgyLCJqdGkiOiI4YWExZjE5OC04ZGU1LTRiYzUtOTdkNi05NmVlNGY1YmE2MjYifQ.oyMawfzv7FgXztSGjzFcTuYSgDz82pqH1_tVMZ5xvedEwewLvPtNbxjKz4jo91m2PO0GJHpguSdhfdCLTACa_MKlZvZdwugeN2mowxLhyMi1Mg2md-ZMS3GY8A6q9n_0q_-7xvtCwoumPg4CGL63eTOXStY2BX0jh3ieESVdRJK7VQXGkUjDjoqYlvpEePnmnHK-pt2sBMR4PrLyz13BqOBsvT9vJTahUsmgTdSuUbKa8iZyD9ZzIUli75H8RzCmQviYjAMrOlFiBxLpUXekkqYzJ7PyswaQ1y3C09aALO_5777wlpGAwz-AM7I9-XUgTGmtcjqt4e2-vsB7eDT7Yw";

    @Test
    public void testFindAll() {
        given()
                .header("Authorization", "Bearer " + token)  // Autorização com o token
                .when().get("/lotes")
                .then().statusCode(200);
    }

  
    @Test
    public void testUpdateLote() {
        LoteRequestDTO dto = new LoteRequestDTO(
                "L12345",
                100,
                LocalDate.of(2024, 10, 22),
                1L // id da placa de vídeo (utilizando 1 como exemplo)
        );

        long id = loteService.create(dto).getId();

        LoteRequestDTO novoDto = new LoteRequestDTO(
                "L67890",
                200,
                LocalDate.of(2025, 10, 22),
                1L // id da placa de vídeo (utilizando 1 como exemplo)
        );

        given()
                .header("Authorization", "Bearer " + token)  // Autorização com o token
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/lotes/" + id)
                .then()
                .statusCode(204);

        Lote lote = loteService.findById(id);

        assertEquals(lote.getCodigo(), "L67890");
        assertEquals(lote.getEstoque(), Integer.valueOf(200));
        assertEquals(lote.getDataFabricacao(), LocalDate.of(2025, 10, 22));

        loteService.delete(lote.getId());
    }

    @Test
    public void testDeleteLote() {
        LoteRequestDTO dto = new LoteRequestDTO(
                "L12345",
                100,
                LocalDate.of(2024, 10, 22),
                1L // id da placa de vídeo (utilizando 1 como exemplo)
        );

        long id = loteService.create(dto).getId();

        given()
                .header("Authorization", "Bearer " + token)  // Autorização com o token
                .when()
                .delete("/lotes/" + id)
                .then()
                .statusCode(204);

        Lote lote = loteService.findById(id);
        assertNull(lote);
    }

    
}
