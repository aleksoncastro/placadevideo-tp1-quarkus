package br.unitins.tp1.placadevideo.resource.usuario;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.placadevideo.dto.request.EmailPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.SenhaPatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.UsernamePatchRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.UsuarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.ClienteResponseDTO;
import br.unitins.tp1.placadevideo.dto.response.UsuarioResponseDTO;
import br.unitins.tp1.placadevideo.form.ImageForm;
import br.unitins.tp1.placadevideo.repository.usuario.UsuarioRepository;
import br.unitins.tp1.placadevideo.resource.placadevideo.PlacaDeVideoResource;
import br.unitins.tp1.placadevideo.service.fileservice.FileServiceUsuario;
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
import jakarta.ws.rs.core.Response.ResponseBuilder;
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

    @Inject
    FileServiceUsuario fileService;

    @Inject
    UsuarioRepository usuarioRepository;

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

    @GET
    @Path("/me")
    @RolesAllowed({ "User" })
    public Response findByMe() {
        String username = jsonWebToken.getSubject();
        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findByUsername(username))).build();
    }

    @POST
    @Path("/funcionarios")
    @RolesAllowed("Adm")
    public Response registrarFuncionario(@Valid UsuarioRequestDTO dto) {
        LOG.info("Registrando funcionario no metodo registrarFuncionario");
        return Response.status(Status.CREATED).entity(UsuarioResponseDTO.valueOf(usuarioService.createFuncionario(dto)))
                .build();
    }

    @PATCH
    @RolesAllowed({ "User", "Adm" })
    @Path("/update/email")
    public Response updateEmail(EmailPatchRequestDTO dto) {
        LOG.info("Execucao do metodo updateEmail");
        String username = jsonWebToken.getSubject();

        usuarioService.updateEmail(username, dto);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({ "User", "Adm" })
    @Path("/update/username")
    public Response updateUsername(UsernamePatchRequestDTO dto) {
        LOG.info("Execucao do metodo updateEmail");
        String username = jsonWebToken.getSubject();

        usuarioService.updateUsername(username, dto);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({ "User", "Adm" })
    @Path("/update/senha")
    public Response updateSenha(SenhaPatchRequestDTO dto) {
        LOG.info("Execucao do metodo updateSenha");
        String username = jsonWebToken.getSubject();

        usuarioService.updateSenha(username, dto);
        return Response.noContent().build();
    }

    @GET
    @Path("/search/{cpf}")
    @RolesAllowed({ "Adm" })
    public Response findByCpf(@PathParam("cpf") String cpf) {
        LOG.infof("Buscando cliente com o cpf %s", cpf);
        return Response.ok(UsuarioResponseDTO.valueOf(usuarioService.findByCpf(cpf))).build();
    }

    @PATCH
    @RolesAllowed({ "Adm", "User" })
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form) {
        try {
            fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }
    }

    @PATCH
    @RolesAllowed({ "Adm", "User" })
    @Path("/image/delete/{nomeImagem}/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response deleteImage(@PathParam("nomeImagem") String nomeImagem,
            @PathParam("idUsuario") Long idUsuario) {
        try {
            fileService.deletarImagem(idUsuario, nomeImagem);
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }

    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }
}
