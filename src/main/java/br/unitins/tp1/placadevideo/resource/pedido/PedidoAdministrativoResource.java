package br.unitins.tp1.placadevideo.resource.pedido;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.placadevideo.dto.Request.PedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Request.StatusPedidoRequestDTO;
import br.unitins.tp1.placadevideo.dto.Response.PedidoResponseDTO;
import br.unitins.tp1.placadevideo.service.pedido.PedidoService;
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

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoAdministrativoResource {


    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jwt;

    @GET
    // @RolesAllowed("User")
    public Response findByUsername() {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        return Response
                .ok(pedidoService.findByUsername(username).stream().map(o -> PedidoResponseDTO.valueOf(o)).toList())
                .build();
    }

    @POST
    // @RolesAllowed("User")
    public Response create(@Valid PedidoRequestDTO dto) {
        // buscando o username do hash do jwt
        String username = jwt.getSubject();
        return Response.status(Status.CREATED).entity(
                PedidoResponseDTO.valueOf(pedidoService.create(dto, username))).build();

    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
       
        return Response.ok(pedidoService.findAll().stream().map(o -> PedidoResponseDTO.valueOf(o)).toList()).build();
    }

    @GET
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long idPlacaDeVideo) {
        return Response.ok(pedidoService.findByItem(idPlacaDeVideo)).build();
    }

    @GET
    @Path("/search/itemn/{id}")
    public Response findByStatus(@PathParam("id") int idStatus) {
        return Response.ok(pedidoService.findByStatus(idStatus)).build();
    }

    //@PATCH
    //@RolesAllowed({"Funcionario"})
    @Path("/{id}/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, StatusPedidoRequestDTO dto){
        return Response.ok(pedidoService.updateStatusPedido(idPedido, dto)).build();
    }

}
