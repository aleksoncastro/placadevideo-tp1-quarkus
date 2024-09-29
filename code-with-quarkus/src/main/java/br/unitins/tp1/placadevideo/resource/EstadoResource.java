package br.unitins.tp1.placadevideo.resource;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.EstadoDTORequest;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    public Estado findById(@PathParam("id") Long id) {
        return estadoService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Estado> findByNome(@PathParam("nome") String nome) {
        return estadoService.findByNome(nome);
    }

    @GET
    public List<Estado> findAll() {
        return estadoService.findAll();
    }

    @POST
    public Estado create(EstadoDTORequest estado) {

        return estadoService.create(estado);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, EstadoDTORequest estado) {
        estadoService.update(id, estado);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        estadoService.delete(id);
    }
}
