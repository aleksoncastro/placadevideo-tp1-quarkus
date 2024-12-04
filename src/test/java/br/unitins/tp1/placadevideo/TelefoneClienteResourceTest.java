package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class TelefoneClienteResourceTest {

    @Inject
    public TelefoneClienteService telefoneClienteService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/telefonesclientes")
                .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        long idCliente = 1L; // ID fictício do cliente
    
        TelefoneClienteRequestDTO dto = new TelefoneClienteRequestDTO(
                "11", 
                "987654321" 
        );
    
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/telefonesclientes/" + idCliente) 
                .then()
                .statusCode(201) 
                .body("id", notNullValue(), 
                        "codigoArea", is("11"),
                        "numero", is("987654321")); 
    
       

    }


    @Test
    public void testUpdateTelefoneCliente() {
    
        TelefoneClienteRequestDTO dto = new TelefoneClienteRequestDTO(
                "11", // Código de área (deve ter 2 dígitos)
                "987654321" // Número (deve ter entre 9 e 11 dígitos)
        );
    
        TelefoneCliente telefoneCliente = telefoneClienteService.create(dto); 
    
        // Novo DTO para a atualização
        TelefoneClienteRequestDTO novoDto = new TelefoneClienteRequestDTO(
                "21", 
                "123456789"
        );
    
        // Atualizar o telefone cliente
        given()
                .contentType(ContentType.JSON)
                .body(novoDto)
                .when()
                .put("/telefonesclientes/" + telefoneCliente.getId())
                .then()
                .statusCode(204); 
    
        // Verificar se a atualização foi realizada com sucesso
        TelefoneCliente telefoneAtualizado = telefoneClienteService.findById(telefoneCliente.getId());
    
        assertEquals(telefoneAtualizado.getCodigoArea(), "21");
        assertEquals(telefoneAtualizado.getNumero(), "123456789"); 
    
        // Remover o telefone cliente inserido
        telefoneClienteService.delete(telefoneAtualizado.getId());
    }


    

    @Test
    public void testDelete() {
        TelefoneClienteRequestDTO dto = new TelefoneClienteRequestDTO(
                "11", // Código de área
                "912345678" // Número de telefone
        );

        long id = telefoneClienteService.create(dto).getId();

        given()
                .when()
                .delete("/telefonesclientes/" + id)
                .then()
                .statusCode(204);

        TelefoneCliente telefonecliente = telefoneClienteService.findById(id);
        assertNull(telefonecliente);
    }

   

}
