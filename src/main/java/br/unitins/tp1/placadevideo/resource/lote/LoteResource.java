package br.unitins.tp1.placadevideo.resource.lote;

import br.unitins.tp1.placadevideo.dto.Request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.dto.Response.LoteResponseDTO;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
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

@Path("/lotes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoteResource {

    @Inject
    public LoteService loteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(LoteResponseDTO.valueOf(loteService.findById(id))).build();
    }

    @GET
    @Path("/search/codigo/{codigo}")
    public Response findById(@PathParam("codigo") String codigo) {
        return Response.ok(LoteResponseDTO.valueOf(loteService.findByCodigo(codigo))).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(loteService.findAll().stream().map(o -> LoteResponseDTO.valueOf(o)).toList()).build();
    }

    @POST
    public Response create(@Valid LoteRequestDTO dto) {
        return Response.status(Status.CREATED).entity(LoteResponseDTO.valueOf(loteService.create(dto))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid LoteRequestDTO dto) {
        loteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        loteService.delete(id);
        return Response.noContent().build();
    }

}