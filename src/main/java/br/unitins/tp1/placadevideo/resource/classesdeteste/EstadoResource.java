package br.unitins.tp1.placadevideo.resource.classesdeteste;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.EstadoResponseDTO;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
//import jakarta.annotation.security.RolesAllowed;
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
    //@RolesAllowed("Adm")
    public Estado findById(@PathParam("id") Long id) {
        return estadoService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    //@RolesAllowed("{Adm, User}")
    public List<Estado> findByNome(@PathParam("nome") String nome) {
        return estadoService.findByNome(nome);
    }

    @GET
    public List<Estado> findAll() {
        return estadoService.findAll();
    }

    @POST
    public EstadoResponseDTO create(EstadoRequestDTO dto) {
        return EstadoResponseDTO.valueOf(estadoService.create(dto));
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, EstadoRequestDTO estado) {
        estadoService.update(id, estado);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        estadoService.delete(id);
    }
}
