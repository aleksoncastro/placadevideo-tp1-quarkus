package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.TelefoneFornecedor;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class TelefoneFornecedorResourceTest {

    @Inject
    public TelefoneFornecedorService telefoneFornecedorService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/telefonesfornecedores")
                .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        long idFornecedor = 1L; // ID fictício do fornecedor
    
        TelefoneFornecedorRequestDTO dto = new TelefoneFornecedorRequestDTO(
                "11", 
                "987654321" 
        );
    
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/telefonesfornecedores/" + idFornecedor) 
                .then()
                .statusCode(201) 
                .body("id", notNullValue(), 
                        "codigoArea", is("11"),
                        "numero", is("987654321")); 
    
       
        TelefoneFornecedor telefoneFornecedor = telefoneFornecedorService.findByNumero("987654321");
        if (telefoneFornecedor != null) {
            telefoneFornecedorService.delete(telefoneFornecedor.getId());
        }
    }


    @Test
    public void testUpdateTelefoneFornecedor() {
    
        TelefoneFornecedorRequestDTO dto = new TelefoneFornecedorRequestDTO(
                "11", // Código de área (deve ter 2 dígitos)
                "987654321" // Número (deve ter entre 9 e 11 dígitos)
        );
    
        TelefoneFornecedor telefoneFornecedor = telefoneFornecedorService.create(dto); 
    
        // Novo DTO para a atualização
        TelefoneFornecedorRequestDTO novoDto = new TelefoneFornecedorRequestDTO(
                "21", 
                "123456789"
        );
    
        // Atualizar o telefone fornecedor
        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/telefonesfornecedores/" + telefoneFornecedor.getId())
                .then()
                .statusCode(204); 
    
        // Verificar se a atualização foi realizada com sucesso
        TelefoneFornecedor telefoneAtualizado = telefoneFornecedorService.findById(telefoneFornecedor.getId());
    
        assertEquals(telefoneAtualizado.getCodigoArea(), "21");
        assertEquals(telefoneAtualizado.getNumero(), "123456789"); 
    
        // Remover o telefone fornecedor inserido
        telefoneFornecedorService.delete(telefoneAtualizado.getId());
    }


    

    @Test
    public void testDelete() {
        TelefoneFornecedorRequestDTO dto = new TelefoneFornecedorRequestDTO(
                "11", // Código de área
                "912345678" // Número de telefone
        );

        long id = telefoneFornecedorService.create(dto).getId();

        given()
                .when()
                .delete("/telefonesfornecedores/" + id)
                .then()
                .statusCode(204);

        TelefoneFornecedor telefonefornecedor = telefoneFornecedorService.findById(id);
        assertNull(telefonefornecedor);
    }

   
}
