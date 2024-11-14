package br.unitins.tp1.placadevideo.resource.funcionario;

import br.unitins.tp1.placadevideo.dto.Request.FuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.Response.FuncionarioResponseDTO;
import br.unitins.tp1.placadevideo.service.funcionario.FuncionarioService;
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

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    public FuncionarioService funcionarioService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FuncionarioResponseDTO.valueOf(funcionarioService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(funcionarioService.findByNome(nome).stream().map(o -> FuncionarioResponseDTO.valueOf(o)).toList())
                .build();
    }

    @GET
    @Path("/search/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf) {
        return Response.ok(FuncionarioResponseDTO.valueOf(funcionarioService.findByCpf(cpf))).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(funcionarioService.findAll().stream().map(o -> FuncionarioResponseDTO.valueOf(o)).toList()).build();
    }

    @POST
    public Response create(@Valid FuncionarioRequestDTO dto) {
        return Response.status(Status.CREATED).entity(FuncionarioResponseDTO.valueOf(funcionarioService.create(dto))).build();
    }

    @POST
    @Path("/{id}/telefones")
    public Response addEndereco(@PathParam("id") Long funcionarioId, @Valid TelefoneFuncionarioRequestDTO telefoneDTO) {
        funcionarioService.addTelefone(funcionarioId, telefoneDTO);
        ;
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}/telefone/{telefoneId}")
    public Response update(
            @PathParam("id") Long id,
            @PathParam("telefoneId") Long telefoneId,
            @Valid FuncionarioRequestDTO dto) {
        funcionarioService.update(id, telefoneId, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        funcionarioService.delete(id);
        return Response.noContent().build();
    }

}