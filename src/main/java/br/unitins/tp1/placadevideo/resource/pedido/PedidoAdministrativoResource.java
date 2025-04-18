package br.unitins.tp1.placadevideo.resource.pedido;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.response.PedidoResponseDTO;
import br.unitins.tp1.placadevideo.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoAdministrativoResource {

    private static final Logger LOG = Logger.getLogger(PedidoResourceCliente.class.getName());

    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    ////@RolesAllowed({ "Adm" })
    public Response findByUsername(String username) {
        // buscando o username do hash do jwt
        LOG.infof("Buscando pedidos pelo cliente com id %d", username);
        return Response
                .ok(pedidoService.findByUsername(username).stream().map(o -> PedidoResponseDTO.valueOf(o)).toList())
                .build();
    }

    @GET
    @Path("/{id}")
    //@RolesAllowed("Adm")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando pedido com id %d", id);
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    //@RolesAllowed({ "Adm" })
    public Response findAll() {
        LOG.info("Buscando todos os pedidos");
        return Response.ok(pedidoService.findAll().stream().map(o -> PedidoResponseDTO.valueOf(o)).toList()).build();
    }

    @GET
    //@RolesAllowed({ "Adm" })
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long idPlacaDeVideo) {
        LOG.infof("Buscando pedidos pelo item com id %d", idPlacaDeVideo);
        return Response.ok(pedidoService.findByItem(idPlacaDeVideo)).build();
    }

    @GET
    //@RolesAllowed({ "Adm" })
    @Path("/search/status/{id}")
    public Response findByStatus(@PathParam("id") int idStatus) {
        LOG.infof("Buscando pedidos pelo status com id %d", idStatus);
        return Response.ok(pedidoService.findByStatus(idStatus)).build();
    }

    @PATCH
    //@RolesAllowed({ "Adm" })
    @Path("/{id}/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, Integer id) {
        LOG.infof("Atualizando status do pedido com id %d para status %d", idPedido, id);
        return Response.ok(pedidoService.updateStatusPedido(idPedido, id)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response cancelarPedido(@PathParam("id") Long id) {
        LOG.infof("Buscando pedidos pelo status com id %d", id);
        pedidoService.cancelarPedido(id);
        ;
        return Response.noContent().build();
    }

}
