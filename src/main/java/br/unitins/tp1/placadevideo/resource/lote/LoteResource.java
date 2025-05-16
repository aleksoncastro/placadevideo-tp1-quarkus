package br.unitins.tp1.placadevideo.resource.lote;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.PaginacaoDTO;
import br.unitins.tp1.placadevideo.dto.response.LoteResponseDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
import br.unitins.tp1.placadevideo.service.lote.LoteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
import jakarta.ws.rs.core.Response.Status;

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
    @Path("/codigo/{codigo}")
    // @RolesAllowed({"Adm"})
    public List<Lote> findByCodigo(@PathParam("codigo") String codigo,  @QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        LOG.infof("Buscando lote pelo código %s", codigo);
        return loteService.findByCodigo(codigo, page, pageSize);
    }

    @GET
    // @RolesAllowed({ "Adm", "User" })
    public PaginacaoDTO findAll(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return loteService.findAll(page, pageSize); // Agora retorna o DTO de paginação
    }

    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LoteResponseDTO> findPage(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("20") int pageSize) {
        return loteService.findPage(page, pageSize);
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

    @GET
    @Path("/count")
    public long total() {
        return loteService.count();
    }

    @GET
    @Path("/nome/{nome}/count")
    public long totalPorNome(String nome) {
        return loteService.count(nome);
    }
}