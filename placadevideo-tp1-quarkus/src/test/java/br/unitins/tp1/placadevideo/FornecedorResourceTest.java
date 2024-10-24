package br.unitins.tp1.placadevideo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.placadevideo.dto.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.resource.fornecedor.FornecedorResource;
import br.unitins.tp1.placadevideo.service.endereco.EnderecoService;
import br.unitins.tp1.placadevideo.service.fornecedor.FornecedorService;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFornecedorService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class FornecedorResourceTest {

        @Inject
        public FornecedorService fornecedorService;

        @Inject
        public EnderecoService enderecoService;

        @Inject
        public TelefoneFornecedorService telefoneFornecedorService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/pessoasfisicas")
                                .then().statusCode(200);
        }

        @Test
        public void testCreateFornecedor() {
                // Criando um objeto FornecedorRequestDTO
                FornecedorRequestDTO dto = new FornecedorRequestDTO(
                                "Fornecedor XYZ", // Nome do fornecedor
                                "12.345.678/0001-90", // CNPJ do fornecedor
                                "fornecedor@example.com", // Email do fornecedor
                                List.of(new TelefoneFornecedorRequestDTO(
                                                "11", // Código de área
                                                "91234-5678" // Número de telefone
                                )));

                given()
                                .contentType(ContentType.JSON)
                                .body(dto)
                                .when()
                                .post("/fornecedores") // Supondo que o endpoint seja /fornecedores
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue(),
                                                "nome", is("Fornecedor XYZ"),
                                                "cnpj", is("12.345.678/0001-90"),
                                                "email", is("fornecedor@example.com"),
                                                "telefones.numero", hasItem(is("91234-5678")));

                // Remover o fornecedor inserido
                fornecedorService.delete(fornecedorService.findByCnpj("12.345.678/0001-90").getId());
        }

        @Test
        public void testUpdate() {
                // Criando um objeto FornecedorRequestDTO inicial
                FornecedorRequestDTO dto = new FornecedorRequestDTO(
                                "Fornecedor XYZ", // Nome do fornecedor
                                "12.345.678/0001-90", // CNPJ do fornecedor
                                "fornecedor@example.com", // Email do fornecedor
                                List.of(new TelefoneFornecedorRequestDTO(
                                                "11", // Código de área
                                                "91234-5678" // Número de telefone
                                )));

                // Criar o fornecedor
                long id = fornecedorService.create(dto).getId();

                // Criando um novo objeto FornecedorRequestDTO para atualização
                FornecedorRequestDTO novoDto = new FornecedorRequestDTO(
                                "Cleitin", 
                                "12.375.568/0001-90",
                                "fornecedor2@example.com", 
                                List.of(new TelefoneFornecedorRequestDTO(
                                                "12", 
                                                "91234-5698" 
                                )));

                long telefoneId = 1l;

                // Atualizando o fornecedor, endereço e telefone
                given()
                                .contentType(ContentType.JSON)
                                .body(novoDto)
                                .when()
                                .put("/fornecedores/" + id + "/telefone/" + telefoneId) 
                                .then()
                                .statusCode(204); 

                // Verificando se os dados do fornecedor foram atualizados
                Fornecedor fornecedorAtualizado = fornecedorService.findById(id);

                assertEquals(fornecedorAtualizado.getNome(), "Cleitin");
                assertEquals(fornecedorAtualizado.getEmail(), "fornecedor2@example.com");

                // Remover o fornecedor inserido
                fornecedorService.delete(fornecedorAtualizado.getId());
        }

        @Test
        public void testDelete() {
                
                FornecedorRequestDTO dto = new FornecedorRequestDTO(
                                "Fornecedor XYZ",
                                "12.345.678/0001-90", 
                                "fornecedor@example.com",
                                List.of(new TelefoneFornecedorRequestDTO(
                                                "11", 
                                                "91234-5678"
                                )));

                long id = fornecedorService.create(dto).getId();

                given()
                                .when()
                                .delete("/fornecedores/" + id)
                                .then().statusCode(204);

                Fornecedor fornecedor = fornecedorService.findById(id);
                assertNull(fornecedor);
        }

        @Test
        @TestHTTPEndpoint(FornecedorResource.class)
        public void testFindAll2() {
                given()
                                .when().get()
                                .then()
                                .statusCode(200)
                                .body("$.size()", greaterThanOrEqualTo(1))
                                .body("[1].nome", is("Gadget Store"));
        }

}
