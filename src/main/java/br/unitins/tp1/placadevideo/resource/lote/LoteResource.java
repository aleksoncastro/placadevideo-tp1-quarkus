package br.unitins.tp1.placadevideo.resource.lote;

import br.unitins.tp1.placadevideo.dto.request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.LoteResponseDTO;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import jakarta.annotation.security.RolesAllowed;
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
import org.jboss.logging.Logger;

@Path("/lotes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoteResource {

    @Inject
    public LoteService loteService;

    private static final Logger LOG = Logger.getLogger(LoteResource.class);

    @GET
    @Path("/{id}")
    // @RolesAllowed({"Adm"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando lote com id %d", id);
        return Response.ok(LoteResponseDTO.valueOf(loteService.findById(id))).build();
    }

    @GET
    @Path("/search/codigo/{codigo}")
    // @RolesAllowed({"Adm"})
    public Response findByCodigo(@PathParam("codigo") String codigo) {
        LOG.infof("Buscando lote pelo código %s", codigo);
        return Response.ok(LoteResponseDTO.valueOf(loteService.findByCodigo(codigo))).build();
    }

    @GET
    // @RolesAllowed({"Adm"})
    public Response findAll() {
        LOG.info("Buscando todos os lotes");
        return Response.ok(loteService.findAll().stream().map(o -> LoteResponseDTO.valueOf(o)).toList()).build();
    }

    @POST
    // @RolesAllowed({"Adm"})
    public Response create(@Valid LoteRequestDTO dto) {
        LOG.info("Criando novo lote");
        return Response.status(Status.CREATED).entity(LoteResponseDTO.valueOf(loteService.create(dto))).build();
    }

    @PUT
    @Path("/{id}")
    // @RolesAllowed({"Adm"})
    public Response update(@PathParam("id") Long id, @Valid LoteRequestDTO dto) {
        LOG.infof("Atualizando lote com id %d", id);
        loteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    // @RolesAllowed({"Adm"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando lote com id %d", id);
        loteService.delete(id);
        return Response.noContent().build();
    }
}