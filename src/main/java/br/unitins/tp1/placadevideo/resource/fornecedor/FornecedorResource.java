package br.unitins.tp1.placadevideo.resource.fornecedor;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.FornecedorResponseDTO;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.service.fornecedor.FornecedorService;
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

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    public FornecedorService fornecedorService;

    private static final Logger LOG = Logger.getLogger(FornecedorResource.class);

    @GET
    @Path("/{id}")
    // @RolesAllowed({"Adm"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando fornecedor com id %d", id);
        return Response.ok(FornecedorResponseDTO.valueOf(fornecedorService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    // @RolesAllowed({"Adm"})
    public List<Fornecedor> findByNome(@PathParam("nome") String nome, @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return fornecedorService.findByNome(nome, page, pageSize);
    }

    @GET
    @Path("/search/{cnpj}")
    // @RolesAllowed({"Adm"})
    public Response findByCnpj(@PathParam("cnpj") String cnpj) {
        LOG.infof("Buscando fornecedor com o CNPJ %s", cnpj);
        return Response.ok(FornecedorResponseDTO.valueOf(fornecedorService.findByCnpj(cnpj))).build();
    }

    @GET
    public List<Fornecedor> findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return fornecedorService.findAll(page, pageSize);
    }

    @POST
    // @RolesAllowed({"Adm"})
    public Response create(@Valid FornecedorRequestDTO dto) {
        LOG.info("Criando novo fornecedor");
        return Response.status(Status.CREATED).entity(FornecedorResponseDTO.valueOf(fornecedorService.create(dto)))
                .build();
    }

    @POST
    @Path("/{id}/telefones")
    // @RolesAllowed({"Adm"})
    public Response addTelefone(@PathParam("id") Long fornecedorId, @Valid TelefoneFornecedorRequestDTO telefoneDTO) {
        LOG.infof("Adicionando telefone para fornecedor com id %d", fornecedorId);
        fornecedorService.addTelefone(fornecedorId, telefoneDTO);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}/telefone/{telefoneId}")
    // @RolesAllowed({"Adm"})
    public Response update(
            @PathParam("id") Long id,
            @PathParam("telefoneId") Long telefoneId,
            @Valid FornecedorRequestDTO dto) {
        LOG.infof("Atualizando fornecedor com id %d e telefoneId %d", id, telefoneId);
        fornecedorService.update(id, telefoneId, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    // @RolesAllowed({"Adm"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando fornecedor com id %d", id);
        fornecedorService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/count")
    public long total() {
        return fornecedorService.count();
    }

    @GET
    @Path("/nome/{nome}/count")
    public long totalPorNome(String nome) {
        return fornecedorService.count(nome);
    }
}