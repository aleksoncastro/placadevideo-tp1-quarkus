package br.unitins.tp1.placadevideo.resource.fornecedor;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.FornecedorResponseDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.service.fornecedor.FornecedorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/fornecedors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    public FornecedorService fornecedorService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FornecedorResponseDTO.valueOf(fornecedorService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        List<Fornecedor> fornecedors = fornecedorService.findByNome(nome);
        return Response.ok(fornecedors.stream().map(FornecedorResponseDTO::valueOf).toList()).build();
    }

    @GET
    public Response findAll() {
        List<Fornecedor> fornecedors = fornecedorService.findAll();
        return Response.ok(fornecedors.stream().map(FornecedorResponseDTO::valueOf).toList()).build();
    }

    @POST
    public Response create(@Valid FornecedorRequestDTO fornecedor) {
        return Response.status(Status.CREATED).entity(FornecedorResponseDTO.valueOf(fornecedorService.create(fornecedor))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FornecedorRequestDTO fornecedor) {
        fornecedorService.update(id, fornecedor);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.noContent().build();
    }

}