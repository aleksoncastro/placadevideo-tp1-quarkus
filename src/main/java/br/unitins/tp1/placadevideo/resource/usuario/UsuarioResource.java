package br.unitins.tp1.placadevideo.resource.usuario;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.EmailPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.SenhaPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.UsuarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.UsuarioResponseDTO;
import br.unitins.tp1.placadevideo.resource.placadevideo.PlacaDeVideoResource;
import br.unitins.tp1.placadevideo.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOG = Logger.getLogger(PlacaDeVideoResource.class);

    @Inject
    public UsuarioService usuarioService;

    @Inject
    public JsonWebToken jsonWebToken;

    @GET
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findById(id))).build();
    }

    @POST
    @Path("/clientes")
    public Response registrarCliente(@Valid UsuarioRequestDTO dto) {
        LOG.info("Registrando funcionario no metodo registerCliente");
        return Response.status(Status.CREATED).entity(UsuarioResponseDTO.valueOf(usuarioService.createCliente(dto)))
                .build();
    }

    @POST
    @Path("/funcionarios")
   // @RolesAllowed("Adm")
    public Response registrarFuncionario(@Valid UsuarioRequestDTO dto) {
        LOG.info("Registrando funcionario no metodo registrarFuncionario");
        return Response.status(Status.CREATED).entity(UsuarioResponseDTO.valueOf(usuarioService.createFuncionario(dto)))
                .build();
    }

    @PATCH
    @RolesAllowed({"User", "Adm"})
    @Path("/update/email")
    public Response updateEmail(EmailPatchRequestDTO dto) {
        LOG.info("Execucao do metodo updateEmail");
        String username = jsonWebToken.getSubject();

        usuarioService.updateEmail(username, dto);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"User", "Adm"})
    @Path("/update/senha")
    public Response updateSenha(SenhaPatchRequestDTO dto) {
        LOG.info("Execucao do metodo updateSenha");
        String username = jsonWebToken.getSubject();

        usuarioService.updateSenha(username, dto);
        return Response.noContent().build();
    }

     @GET
    @Path("/search/{cpf}")
    @RolesAllowed({"Adm"})
    public Response findByCpf(@PathParam("cpf") String cpf) {
        LOG.infof("Buscando cliente com o cpf %s", cpf);
        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findByCpf(cpf))).build();
    }
}


