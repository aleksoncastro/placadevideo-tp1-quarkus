package br.unitins.tp1.placadevideo.resource.pedido;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.EnderecoEntregaRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.EnderecoEntregaResponseDTO;
import br.unitins.tp1.placadevideo.dto.response.ItemPedidoResponsetDTO;
import br.unitins.tp1.placadevideo.dto.response.PedidoResponseDTO;
import br.unitins.tp1.placadevideo.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResourceCliente {
    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(PedidoResourceCliente.class);

    @GET
    @RolesAllowed("User")
    @Path("/search/username")
    public Response findByUsername() {
        LOG.info("Execucao do metodo findByUsername");
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        return Response
                .ok(pedidoService.findByUsername(username).stream().map(o -> PedidoResponseDTO.valueOf(o)).toList())
                .build();
    }

    @GET
    @RolesAllowed({ "User", "Adm" })
    @Path("/search/itemPedido/{idPedido}")
    public Response findItensPedidoByIdPedido(@PathParam("idPedido") Long idPedido) {
        String username = jwt.getSubject();
        LOG.infof("Buscando itensPedido para o usuário: %s", username);
        return Response
                .ok(pedidoService.findByPedidoId(idPedido).stream().map(o -> ItemPedidoResponsetDTO.valueOf(o))
                        .toList())
                .build();
    }

    @POST
    @RolesAllowed("User")
    public Response create(PedidoRequestDTO dto) {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        LOG.infof("Criando pedido para o usuário: %s", username);
        return Response.status(Status.CREATED).entity(
                PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed("User")
    public Response editEnderecoEntrega(@PathParam("id") Long idPedido, EnderecoEntregaRequestDTO dto) {
        LOG.infof("Editando um endereco do pedido : %s", idPedido);
        return Response.status(Status.CREATED).entity(
                EnderecoEntregaResponseDTO.valueOf(pedidoService.editEnderecoEntrega(idPedido, dto))).build();
    }
}