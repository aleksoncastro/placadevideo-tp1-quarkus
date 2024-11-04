/* package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.model.PlacaDeVideo;
import br.unitins.tp1.placadevideo.resource.placadevideo.PlacaDeVideoResource;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PlacaDeVideoResourceTest {

    @Inject
    public PlacaDeVideoService placaDeVideoService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/placasdevideo")
                .then().statusCode(200);
    }

  /*
   *   @Test
    public void testCreate() {
        PlacaDeVideoRequestDTO dto = new PlacaDeVideoRequestDTO(
                1L,
                "Modelo XYZ",
                "Categoria A",
                1500.0,
                8,
                "1920x1080",
                500,
                "Descrição da placa de vídeo",
                1
        );
    
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/placasdevideo")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "modelo", is("Modelo XYZ"),
                        "categoria", is("Categoria A"),
                        "preco", is(1500.0f),
                        "vram", is(8),
                        "resolucao", is("1920x1080"),
                        "energia", is(500),
                        "descricao", is("Descrição da placa de vídeo"),
                        "compatibilidade", is(1));
    
       placaDeVideoService.delete(placaDeVideoService.findByDescricao("Descrição da placa de vídeo").getId());
    }

    @Test
    public void testUpdate() {
        PlacaDeVideoRequestDTO dto = new PlacaDeVideoRequestDTO(
                1L,
                "Modelo XYZ",
                "Categoria A",
                1500.0,
                8,
                "1920x1080",
                500,
                "Descrição da placa de vídeo",
                1
        );
    
        long id = placaDeVideoService.create(dto).getId();
    
        PlacaDeVideoRequestDTO novoDto = new PlacaDeVideoRequestDTO(
                1L,
                "Modelo ABC",
                "Categoria B",
                2000.0,
                10,
                "2560x1440",
                600,
                "Nova descrição da placa de vídeo",
                2
        );
    
        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/placasdevideo/" + id)
                .then()
                .statusCode(204);
    
        PlacaDeVideo placaDeVideo = placaDeVideoService.findById(id);
    
        assertEquals(placaDeVideo.getModelo(), "Modelo ABC");
        assertEquals(placaDeVideo.getCategoria(), "Categoria B");
        assertEquals(placaDeVideo.getPreco(), 2000.00);
        assertEquals(placaDeVideo.getVram(), 10);
        assertEquals(placaDeVideo.getResolucao(), "2560x1440");
        assertEquals(placaDeVideo.getEnergia(), 600);
        assertEquals(placaDeVideo.getDescricao(), "Nova descrição da placa de vídeo");
        assertEquals(placaDeVideo.getCompatibilidade(), 2);
    
        placaDeVideoService.delete(placaDeVideo.getId());
    }

    @Test
    public void testDelete() {
        PlacaDeVideoRequestDTO dto = new PlacaDeVideoRequestDTO(
                1L,
                "Modelo XYZ",
                "Categoria A",
                1500.0,
                8,
                "1920x1080",
                500,
                "Descrição da placa de vídeo",
                1
        );

        long id = placaDeVideoService.create(dto).getId();

        given()
                .when()
                .delete("/placasdevideo/" + id)
                .then()
                .statusCode(204);

        PlacaDeVideo placadevideo = placaDeVideoService.findById(id);
        assertNull(placadevideo);
    }

    @Test
    @TestHTTPEndpoint(PlacaDeVideoResource.class)
    public void testFindAll2() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1))
                .body("[1].descricao", is("GTX 1660"));
    }
}
*/