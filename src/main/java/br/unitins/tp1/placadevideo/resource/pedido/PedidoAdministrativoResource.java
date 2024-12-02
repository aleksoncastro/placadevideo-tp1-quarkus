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

    private static final Logger LOG = Logger.getLogger(PedidoResource.class.getName());

    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @RolesAllowed({"Adm", "User"})
    public Response findByUsername() {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        LOG.infof("Buscando pedidos pelo cliente com id %d", username);
        return Response
                .ok(pedidoService.findByUsername(username).stream().map(o -> PedidoResponseDTO.valueOf(o)).toList())
                .build();
    }

    /*
     * @POST
    @RolesAllowed("User")
    public Response create(@Valid PedidoRequestDTO dto) {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        return Response.status(Status.CREATED).entity(
                PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();

    }
     */

    @GET
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando pedido com id %d", id);
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    public Response findAll() {
        LOG.info("Buscando todos os pedidos");
        return Response.ok(pedidoService.findAll().stream().map(o -> PedidoResponseDTO.valueOf(o)).toList()).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long idPlacaDeVideo) {
        LOG.infof("Buscando pedidos pelo item com id %d", idPlacaDeVideo);
        return Response.ok(pedidoService.findByItem(idPlacaDeVideo)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/search/status/{id}")
    public Response findByStatus(@PathParam("id") int idStatus) {
        LOG.infof("Buscando pedidos pelo status com id %d", idStatus);
        return Response.ok(pedidoService.findByStatus(idStatus)).build();
    }

    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, Integer id){
        LOG.infof("Atualizando status do pedido com id %d para status %d", idPedido, id);
        return Response.ok(pedidoService.updateStatusPedido(idPedido, id)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response cancelarPedido(@PathParam("id") Long id) {
        LOG.infof("Buscando pedidos pelo status com id %d", id);
        pedidoService.cancelarPedido(id);;
        return Response.noContent().build();
    }

    /*@POST
    @RolesAllowed({"User"})
    @Path("/{id}/pagamento/info/boleto")
    public Response gerarBoleto(@PathParam("id") Long id){
        return Response.status(Status.CREATED).entity(pedidoService.gerarBoleto(id)).build();
    }
    
    @POST
    @RolesAllowed({"User"})
    @Path("/{id}/pagamento/info/pix")
    public Response gerarPix(@PathParam("id") Long id){
        return Response.status(Status.CREATED).entity(pedidoService.gerarPix(id)).build();
    } 
 */
    @PATCH
    @RolesAllowed({"User"})
    @Path("/{id}/pagamento/{id-boleto}")
    public Response registrarPagamentoBoleto(@PathParam ("id") Long id, @PathParam("id-boleto") Long idBoleto){
        LOG.infof("Registrando pagamento boleto para pedido com id %d e id boleto %d", id, idBoleto);
        pedidoService.registrarPagamentoBoleto(id, idBoleto);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"User"})
    @Path("/{id}/pagamento/{id-pix}")
    public Response registrarPagamentoPix(@PathParam ("id") Long id, @PathParam("id-pix") Long idPix){
        LOG.infof("Registrando pagamento PIX para pedido com id %d e id PIX %d", id, idPix);
        pedidoService.registrarPagamentoPix(id, idPix);
        return Response.noContent().build();
    }

    /*@PATCH
    @RolesAllowed({"User"})
    @Path("/{id}/pagamento/cartao")
    public Response registrarPagamentoCartao(@PathParam ("id") Long id, Long idCartao){
        pedidoService.registrarPagamentoCartao(id, idCartao);
        return Response.noContent().build();
    } */


}
