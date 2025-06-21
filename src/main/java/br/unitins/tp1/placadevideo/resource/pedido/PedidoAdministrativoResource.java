package br.unitins.tp1.placadevideo.resource.pedido;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.PaginacaoDTO;
import br.unitins.tp1.placadevideo.dto.response.PedidoResponseDTO;
import br.unitins.tp1.placadevideo.dto.response.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.service.pedido.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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
    @RolesAllowed({ "Adm" })
    public Response findByUsername(String username) {
        // buscando o username do hash do jwt
        LOG.infof("Buscando pedidos pelo cliente com id %d", username);
        return Response
                .ok(pedidoService.findByUsername(username).stream().map(o -> PedidoResponseDTO.valueOf(o)).toList())
                .build();
    }

    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PedidoResponseDTO> findPage(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("20") int pageSize) {
        return pedidoService.findPage(page, pageSize);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando pedido com id %d", id);
        return Response.ok(PedidoResponseDTO.valueOf(pedidoService.findById(id))).build();
    }

    @GET
    @Path("/all")
    @RolesAllowed({ "Adm" })
    public PaginacaoDTO findAll(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return pedidoService.findAll(page, pageSize); // Agora retorna o DTO de paginação
    }

    @GET
    @RolesAllowed({ "Adm" })
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long idPlacaDeVideo) {
        LOG.infof("Buscando pedidos pelo item com id %d", idPlacaDeVideo);
        return Response.ok(pedidoService.findByItem(idPlacaDeVideo)).build();
    }

    @GET
    @RolesAllowed({ "Adm" })
    @Path("/search/status/{id}")
    public Response findByStatus(@PathParam("id") int idStatus) {
        LOG.infof("Buscando pedidos pelo status com id %d", idStatus);
        return Response.ok(pedidoService.findByStatus(idStatus)).build();
    }

    @PATCH
    @Consumes(MediaType.WILDCARD)
    @RolesAllowed({ "Adm" })
    @Path("/{idPedido}/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("idPedido") Long idPedido, @PathParam("id") Integer id) {
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
