package br.unitins.tp1.placadevideo.resource.classesdeteste;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.EstadoResponseDTO;
import br.unitins.tp1.placadevideo.dto.response.PageResponse;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
//import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    // @RolesAllowed("Adm")
    public Estado findById(@PathParam("id") Long id) {
        return estadoService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    // @RolesAllowed("{Adm, User}")
    public List<Estado> findByNome(@PathParam("nome") String nome, @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return estadoService.findByNome(nome, page, pageSize);
    }

    @GET
    public Response findAll(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("page_size") @DefaultValue("100") int pageSize
    ) {
        Long totalCount = estadoService.count();
        PageResponse<EstadoResponseDTO> pageResponse = PageResponse.valueOf(page, pageSize, totalCount, estadoService.findAll(page, pageSize).stream().map(EstadoResponseDTO::valueOf).toList());
        return Response.ok(pageResponse).build();
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

    @GET
     @Path("/count")
     public long total() {
         return estadoService.count();
     }
 
     @GET
     @Path("/nome/{nome}/count")
     public long totalPorNome(String nome) {
         return estadoService.count(nome);
     }
 
}
