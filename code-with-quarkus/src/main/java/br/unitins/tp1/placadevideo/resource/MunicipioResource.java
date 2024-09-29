package br.unitins.tp1.placadevideo.resource;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Municipio;
import br.unitins.tp1.placadevideo.service.MunicipioService;
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

@Path("/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    @Inject
    public MunicipioService municipioService;

    @GET
    @Path("/{id}")
    public Municipio findById(@PathParam("id") Long id) {
        return municipioService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Municipio> findByNome(@PathParam("nome") String nome) {
        return municipioService.findByNome(nome);
    }

    @GET
    public List<Municipio> findAll() {
        return municipioService.findAll();
    }

    @POST
    public Municipio create(Municipio municipio) {
        return municipioService.create(municipio);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, Municipio municipio) {
        municipioService.update(municipio);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        municipioService.delete(id);
    }

}
