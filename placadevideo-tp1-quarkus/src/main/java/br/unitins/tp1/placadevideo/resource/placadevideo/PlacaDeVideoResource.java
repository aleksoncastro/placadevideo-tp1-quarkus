package br.unitins.tp1.placadevideo.resource.placadevideo;

import br.unitins.tp1.placadevideo.dto.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
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

@Path("/placasdevideos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlacaDeVideoResource {

    @Inject
    public PlacaDeVideoService placadevideoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placadevideoService.findById(id))).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao) {
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placadevideoService.findByDescricao(descricao))).build();
    }

    @GET
    @Path("/search/{modelo}")
    public Response findByNome(@PathParam("modelo") String modelo) {
        return Response.ok(placadevideoService.findByModelo(modelo).
                                stream().
                                map(o -> PlacaDeVideoResponseDTO.valueOf(o)).
                                toList())
                                .build();
    }

    @GET
    public Response findAll() {
        return Response.ok(placadevideoService.findAll().
                                stream().
                                map(o -> PlacaDeVideoResponseDTO.valueOf(o)).
                                toList()).build();
    }

    @POST
    public Response create(@Valid PlacaDeVideoRequestDTO dto) {
        return Response.status(Status.CREATED).entity(PlacaDeVideoResponseDTO.valueOf(placadevideoService.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid PlacaDeVideoRequestDTO dto){
        placadevideoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        placadevideoService.delete(id);
        return Response.noContent().build();
    }

}