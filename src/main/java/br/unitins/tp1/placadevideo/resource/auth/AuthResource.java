package br.unitins.tp1.placadevideo.resource.auth;

import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.AuthRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.UsuarioResponseDTO;
import br.unitins.tp1.placadevideo.model.usuario.Perfil;
import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import br.unitins.tp1.placadevideo.resource.cliente.ClienteResource;
import br.unitins.tp1.placadevideo.service.hash.HashService;
import br.unitins.tp1.placadevideo.service.jwt.JwtService;
import br.unitins.tp1.placadevideo.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    // Login para clientes
    @POST
    @Path("/cliente")
    public Response loginCliente(AuthRequestDTO authDTO) {
        LOG.info("Executando login de cliente");
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);
        if (usuario == null) {
            return Response.status(Status.NOT_FOUND).entity("Usuario não encontrado").build();
        }

        // Verifica se tem a role USER (cliente)
        if (usuario.getPerfil() != Perfil.USER) {
            return Response.status(Status.UNAUTHORIZED).entity("Acesso restrito a clientes").build();
        }

        return Response.ok(UsuarioResponseDTO.valueOf(usuario))
                .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(usuario)))
                .build();
    }

    // Login para admins e funcionários
    @POST
    @Path("/admin")
    public Response loginAdmin(AuthRequestDTO authDTO) {
        LOG.info("Executando login de admin");
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);
        if (usuario == null) {
            return Response.status(Status.NOT_FOUND).entity("Usuario não encontrado").build();
        }

        // Verifica se tem a role ADM
        if (usuario.getPerfil() != Perfil.ADM) {
            return Response.status(Status.UNAUTHORIZED).entity("Acesso restrito a administradores").build();
        }

        return Response.ok(UsuarioResponseDTO.valueOf(usuario))
                .header("Authorization", jwtService.generateJwt(UsuarioResponseDTO.valueOf(usuario)))
                .build();
    }
}
