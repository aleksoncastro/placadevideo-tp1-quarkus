package br.unitins.tp1.placadevideo.resource.cartao;

import br.unitins.tp1.placadevideo.dto.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.dto.CartaoResponseDTO;
import br.unitins.tp1.placadevideo.service.cartao.CartaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/cartoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoResource {

    @Inject
    public CartaoService cartaoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(CartaoResponseDTO.valueOf(cartaoService.findById(id))).build();
    }

    @GET
    @Path("/search/{titular}")
    public Response findByNome(@PathParam("titular") String titular) {
        return Response.ok(cartaoService.findByTitular(titular).
                                stream().
                                map(o -> CartaoResponseDTO.valueOf(o)).
                                toList())
                                .build();
    }

    @GET
    public Response findAll() {
        return Response.ok(cartaoService.findAll().
                                stream().
                                map(o -> CartaoResponseDTO.valueOf(o)).
                                toList()).build();
    }

    @POST
    public Response create(@Valid CartaoRequestDTO dto) {
        return Response.status(Status.CREATED).entity(CartaoResponseDTO.valueOf(cartaoService.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CartaoRequestDTO dto) {
        cartaoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cartaoService.delete(id);
        return Response.noContent().build();
    }

}