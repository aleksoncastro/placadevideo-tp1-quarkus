package br.unitins.tp1.placadevideo.resource.classesdeteste;

import br.unitins.tp1.placadevideo.dto.request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.TelefoneFuncionarioResponseDTO;
import br.unitins.tp1.placadevideo.service.telefone.TelefoneFuncionarioService;
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

@Path("/telefonesfuncionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneFuncionarioResource {

    @Inject
    public TelefoneFuncionarioService telefonefuncionarioService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(TelefoneFuncionarioResponseDTO.valueOf(telefonefuncionarioService.findById(id))).build();
    }

    @GET
    @Path("/search/numero/{numero}")
    public Response findByNumero(@PathParam("numero") String numero) {
        return Response.ok(TelefoneFuncionarioResponseDTO.valueOf(telefonefuncionarioService.findByNumero(numero))).build();
    }

    @GET
    @Path("/search/{idFuncionario}")
    public Response findByFuncionario(@PathParam("idFuncionario") Long idFuncionario) {
 
        return Response.ok(telefonefuncionarioService.findByFuncionario(idFuncionario).
                                stream().
                                map(o -> TelefoneFuncionarioResponseDTO.valueOf(o)).
                                toList())
                                .build();
    }

    @GET
    public Response findAll() {
        return Response.ok(telefonefuncionarioService.findAll().
                                stream().
                                map(o -> TelefoneFuncionarioResponseDTO.valueOf(o)).
                                toList()).build();
    }

    @POST
    @Path("/{idFuncionario}")
    public Response create(@PathParam("id") Long id, @Valid TelefoneFuncionarioRequestDTO dto) {
        return Response.status(Status.CREATED).entity(TelefoneFuncionarioResponseDTO.valueOf(telefonefuncionarioService.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid TelefoneFuncionarioRequestDTO dto) {
        telefonefuncionarioService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        telefonefuncionarioService.delete(id);
        return Response.noContent().build();
    }

}