package br.unitins.tp1.placadevideo.resource;

import java.util.List;

import br.unitins.tp1.placadevideo.model.PlacaDeVideo;
import br.unitins.tp1.placadevideo.service.PlacaDeVideoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/placasDeVideo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlacaDeVideoResource {

    @Inject
    public PlacaDeVideoService placaDeVideoService;

    @GET
    @Path("/{id}")
    public PlacaDeVideo findById(@PathParam("id") Long id){
        return placaDeVideoService.findById(id);
    }

    @GET
    @Path("/search/{modelo}")
    public List<PlacaDeVideo> findByModelo(@PathParam("modelo") String modelo){
        return placaDeVideoService.findByModelo(modelo);
    }

    @GET
    public List<PlacaDeVideo> findAll(){
        return placaDeVideoService.findAll();
    }

    @POST
    public PlacaDeVideo create(PlacaDeVideo placaDeVideo){
        return placaDeVideoService.create(placaDeVideo);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, PlacaDeVideo placaDeVideo){
        placaDeVideoService.update(placaDeVideo);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        placaDeVideoService.delete(id);
    }
    
}
