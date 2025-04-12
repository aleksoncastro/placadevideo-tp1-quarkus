package br.unitins.tp1.placadevideo.resource.funcionario;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.FuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.FuncionarioResponseDTO;
import br.unitins.tp1.placadevideo.model.usuario.Funcionario;
import br.unitins.tp1.placadevideo.service.funcionario.FuncionarioService;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public JsonWebToken jsonWebToken;

    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);

    @GET
    @Path("/{id}")
    //@RolesAllowed({"Adm"})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando funcionário com id %d", id);
        return Response.ok(FuncionarioResponseDTO.valueOf(funcionarioService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    //@RolesAllowed({"Adm"})
    public List<Funcionario> findByNome(@PathParam("nome") String nome, @QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("page_size") @DefaultValue("100") int pageSize) { 
    return funcionarioService.findByNome(nome, page, pageSize);
    }

    @GET
    @Path("/search/{cpf}")
    @RolesAllowed({"Adm"})
    public Response findByCpf(@PathParam("cpf") String cpf) {
        LOG.infof("Buscando funcionário com o CPF %s", cpf);
        return Response.ok(FuncionarioResponseDTO.valueOf(funcionarioService.findByCpf(cpf))).build();
    }

    @GET
    //@RolesAllowed({"Adm"})
    public List<Funcionario> findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("page_size") @DefaultValue("100") int pageSize) { 
    return funcionarioService.findAll(page, pageSize);
    }

    @GET
     @Path("/count")
     public long total() {
         return funcionarioService.count();
     }
 
     @GET
     @Path("/nome/{nome}/count")
     public long totalPorNome(String nome) {
         return funcionarioService.count(nome);
     }

    @POST
   // @RolesAllowed({"Adm"})
    public Response create(@Valid FuncionarioRequestDTO dto) {
        LOG.info("Criando novo funcionário");
        String username = jsonWebToken.getSubject();
        return Response.status(Status.CREATED).entity(FuncionarioResponseDTO.valueOf(funcionarioService.create(username,dto))).build();
    }

    @POST
    @Path("/{id}/telefones")
   // @RolesAllowed({"Adm"})
    public Response addTelefone(@PathParam("id") Long funcionarioId, @Valid TelefoneFuncionarioRequestDTO telefoneDTO) {
        LOG.infof("Adicionando telefone para funcionário com id %d", funcionarioId);
        funcionarioService.addTelefone(funcionarioId, telefoneDTO);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}/telefone/{telefoneId}")
    //@RolesAllowed({"Adm"})
    public Response update(
            @PathParam("id") Long id,
            @PathParam("telefoneId") Long telefoneId,
            @Valid FuncionarioRequestDTO dto) {
        LOG.infof("Atualizando funcionário com id %d e telefoneId %d", id, telefoneId);
        funcionarioService.update(id, telefoneId, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed({"Adm"})
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando funcionário com id %d", id);
        funcionarioService.delete(id);
        return Response.noContent().build();
    }
}