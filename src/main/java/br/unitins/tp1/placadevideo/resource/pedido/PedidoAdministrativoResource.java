package br.unitins.tp1.placadevideo.resource.pedido;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.placadevideo.dto.Request.StatusPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Response.PedidoResponseDTO;
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
public class PedidoAdministrativoResource {


    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    @RolesAllowed({"Adm", "User"})
    public Response findByUsername() {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
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
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    public Response findAll() {
        return Response.ok(pedidoService.findAll().stream().map(o -> PedidoResponseDTO.valueOf(o)).toList()).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long idPlacaDeVideo) {
        return Response.ok(pedidoService.findByItem(idPlacaDeVideo)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/search/itemn/{id}")
    public Response findByStatus(@PathParam("id") int idStatus) {
        return Response.ok(pedidoService.findByStatus(idStatus)).build();
    }

    @PATCH
    @RolesAllowed({"Adm"})
    @Path("/{id}/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, Integer id){
        return Response.ok(pedidoService.updateStatusPedido(idPedido, id)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response cancelarPedido(@PathParam("id") Long id) {
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
    @Path("/{id}/pagamento/boleto")
    public Response registrarPagamentoBoleto(@PathParam ("id") Long id, Long idBoleto){
        pedidoService.registrarPagamentoBoleto(id, idBoleto);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({"User"})
    @Path("/{id}/pagamento/pix")
    public Response registrarPagamentoPix(@PathParam ("id") Long id, Long idPix){
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
