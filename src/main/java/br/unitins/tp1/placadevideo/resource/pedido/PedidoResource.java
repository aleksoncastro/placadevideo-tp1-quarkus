package br.unitins.tp1.placadevideo.resource.pedido;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.PedidoResponseDTO;
import br.unitins.tp1.placadevideo.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {
    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @GET
    @RolesAllowed("User ")
    public Response findByUsername() {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        LOG.infof("Buscando pedidos para o usuário: %s", username);
        return Response
                .ok(pedidoService.findByUsername(username).stream().map(o -> PedidoResponseDTO.valueOf(o)).toList())
                .build();
    }

    @POST
    @RolesAllowed({ "User ", "Adm" })
    public Response create(@Valid PedidoRequestDTO dto) {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        LOG.infof("Criando pedido para o usuário: %s", username);
        return Response.status(Status.CREATED).entity(
                PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();
    }
}