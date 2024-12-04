package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.MemoriaRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.SaidaVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TamanhoRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class PlacaDeVideoResourceTest {

    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImFkbWluX3VzZXIiLCJncm91cHMiOlsiQWRtIl0sImV4cCI6MTczMzQyNTE4MiwiaWF0IjoxNzMzMzM4NzgyLCJqdGkiOiI4YWExZjE5OC04ZGU1LTRiYzUtOTdkNi05NmVlNGY1YmE2MjYifQ.oyMawfzv7FgXztSGjzFcTuYSgDz82pqH1_tVMZ5xvedEwewLvPtNbxjKz4jo91m2PO0GJHpguSdhfdCLTACa_MKlZvZdwugeN2mowxLhyMi1Mg2md-ZMS3GY8A6q9n_0q_-7xvtCwoumPg4CGL63eTOXStY2BX0jh3ieESVdRJK7VQXGkUjDjoqYlvpEePnmnHK-pt2sBMR4PrLyz13BqOBsvT9vJTahUsmgTdSuUbKa8iZyD9ZzIUli75H8RzCmQviYjAMrOlFiBxLpUXekkqYzJ7PyswaQ1y3C09aALO_5777wlpGAwz-AM7I9-XUgTGmtcjqt4e2-vsB7eDT7Yw";

        @Inject
        public PlacaDeVideoRepository placaDeVideoRepository;

    @Test
    public void testFindAll() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/placasdevideo")
                .then()
                .statusCode(200)
                .body("$", hasSize(2)); // Ajuste para a quantidade esperada no teste
    }

    @Test
    public void testFindById() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/placasdevideo/1")
                .then()
                .statusCode(200)
                .body("id", is(1)); // Ajuste conforme o esperado
    }

    @Test
    public void testCreate() {
        PlacaDeVideoRequestDTO dto = new PlacaDeVideoRequestDTO(
                "Modelo Teste",
                "Categoria Teste",
                new BigDecimal("1500.00"),
                "1920x1080",
                120,
                "Descrição teste",
                1,
                1600.5,
                1800.0,
                1,
                1L,
                true,
                new MemoriaRequestDTO("GDDR6", 8, 256, 14000),
                new TamanhoRequestDTO(10, 15, 20),
                List.of(new SaidaVideoRequestDTO("HDMI", 2))
        );

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().post("/placasdevideo")
                .then()
                .statusCode(201)
                .body("modelo", is("Modelo Teste"));
    }

    @Test
    public void testUpdate() {
        PlacaDeVideoRequestDTO dto = new PlacaDeVideoRequestDTO(
                "Modelo Atualizado",
                "Categoria Atualizada",
                new BigDecimal("2000.00"),
                "2560x1440",
                130,
                "Descrição atualizada",
                2,
                1700.0,
                1900.0,
                2,
                2L,
                false,
                new MemoriaRequestDTO("GDDR6X", 10, 320, 19000),
                new TamanhoRequestDTO(12, 16, 25),
                List.of(new SaidaVideoRequestDTO("DisplayPort", 3))
        );

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().put("/placasdevideo/1")
                .then()
                .statusCode(204);
    }

    @Test
    @Transactional
    public void testDelete() {
        // Cria o DTO para inserir uma nova PlacaDeVideo
        PlacaDeVideoRequestDTO dto = new PlacaDeVideoRequestDTO(
            "Modelo Atualizado",
            "Categoria Atualizada",
            new BigDecimal("2000.00"),
            "2560x1440",
            130,
            "Descrição atualizada",
            2,
            1700.0,
            1900.0,
            2,
            2L,
            false,
            new MemoriaRequestDTO("GDDR6X", 10, 320, 19000),
            new TamanhoRequestDTO(12, 16, 25),
            List.of(new SaidaVideoRequestDTO("DisplayPort", 3))
        );
    
        // Cria a nova PlacaDeVideo no banco
        var response = given()
            .header("Authorization", "Bearer " + token)
            .contentType("application/json")
            .body(dto)
            .when().post("/placasdevideo") // Método para criar uma nova PlacaDeVideo
            .then()
            .statusCode(201) // Verifica se a criação foi bem-sucedida
            .extract()
            .response();
    
        // Obtém o ID da placa criada
        Long idPlaca = response.jsonPath().getLong("id");
        Assertions.assertNotNull(idPlaca, "O ID da PlacaDeVideo criada não deve ser nulo.");
    
        // Deleta a PlacaDeVideo criada
        given()
            .header("Authorization", "Bearer " + token)
            .when()
            .delete("/placasdevideo/" + idPlaca)
            .then()
            .statusCode(204); // Verifica se retorna o código 204 (No Content)
    
        // Confirma que a PlacaDeVideo foi realmente deletada
        PlacaDeVideo deletada = placaDeVideoRepository.findById(idPlaca);
        Assertions.assertNull(deletada, "A PlacaDeVideo deveria ter sido deletada.");
    }
    


}
