package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.PessoaFisicaRequestDTO;
import br.unitins.tp1.placadevideo.model.PessoaFisica;
import br.unitins.tp1.placadevideo.model.Sexo;
import br.unitins.tp1.placadevideo.resource.pessoafisica.PessoaFisicaResource;
import br.unitins.tp1.placadevideo.service.pessoafisica.PessoaFisicaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PessoaFisicaResourceTest {

    @Inject
    public PessoaFisicaService pfService;
   
    @Test
    public void testFindAll(){
        given()
            .when().get("/pessoasfisicas")
            .then().statusCode(200);
    }


    @Test
    public void testCreate(){
        PessoaFisicaRequestDTO dto = 
            new PessoaFisicaRequestDTO("Carlos Henrique", "333", 2);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post("/pessoasfisicas")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Carlos Henrique"),
                      "cpf", is("333"));

        // removendo o dado que foi inserido
        pfService.delete(pfService.findByCpf("333").getId());
    }

    @Test
    public void testUpdate() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        PessoaFisicaRequestDTO dto = 
        new PessoaFisicaRequestDTO("teste", "000.000.000-00", 1);
        
        long id = pfService.create(dto).getId();

        PessoaFisicaRequestDTO novoDto = 
            new PessoaFisicaRequestDTO("Leandra Cavina", "000.000.000-00", 1);

        given()
        .contentType(ContentType.JSON)
        .body(novoDto)
        .when()
            .put("/pessoasfisicas/"+id)
        .then()
            .statusCode(204);

        PessoaFisica pf = pfService.findById(id);

        assertEquals(pf.getNome(), "Leandra Cavina");
        assertEquals(pf.getSexo(), Sexo.FEMININO);

        // removendo o dado que foi inserido
        pfService.delete(pfService.findByCpf("000.000.000-00").getId());

    }

    @Test
    public void testDelete() {
        // inserindo dado para alteracao (evitando a manipulacao de dados)
        PessoaFisicaRequestDTO dto = 
        new PessoaFisicaRequestDTO("teste", "000.000.000-00", 1);
        
        long id = pfService.create(dto).getId();

        given()
        .when()
            .delete("/pessoasfisicas/"+id)
        .then()
            .statusCode(204);

        // verificando se foi apagado no banco de dados
        PessoaFisica pf = pfService.findById(id);
        assertNull(pf);
    
    }
       
    @Test
    @TestHTTPEndpoint(PessoaFisicaResource.class)
    public void testFindAll2(){
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body("$.size()", is(2),
                     "[1].nome", is("Ana Costa"));
    }



}
