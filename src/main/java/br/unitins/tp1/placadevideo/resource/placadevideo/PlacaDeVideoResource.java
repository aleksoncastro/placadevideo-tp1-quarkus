package br.unitins.tp1.placadevideo.resource.placadevideo;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.form.ImageForm;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoFileServiceImpl;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/placasdevideo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlacaDeVideoResource {

    @Inject
    public PlacaDeVideoService placaDeVideoService;

    @Inject
    public PlacaDeVideoFileServiceImpl placaDeVideoFileService;

    private static final Logger LOG = Logger.getLogger(PlacaDeVideoResource.class);

    @GET
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando placa de vídeo com id %d", id);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.findById(id))).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    @RolesAllowed("Adm")
    public Response findByDescricao(@PathParam("descricao") String descricao) {
        LOG.infof("Buscando placa de vídeo pela descrição: %s", descricao);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.findByDescricao(descricao))).build();
    }

    @GET
    @Path("/search/{modelo}")
    @RolesAllowed("Adm")
    public Response findByNome(@PathParam("modelo") String modelo) {
        LOG.infof("Buscando placas de vídeo pelo modelo: %s", modelo);
        return Response
                .ok(placaDeVideoService.findByModelo(modelo).stream().map(o -> PlacaDeVideoResponseDTO.valueOf(o))
                        .toList())
                .build();
    }

    @GET
    @RolesAllowed("Adm")
    public Response findAll() {
        LOG.info("Buscando todas as placas de vídeo");
        return Response.ok(placaDeVideoService.findAll().stream().map(o -> PlacaDeVideoResponseDTO.valueOf(o)).toList())
                .build();
    }

    @POST
    @RolesAllowed("Adm")
    public Response create(@Valid PlacaDeVideoRequestDTO dto) {
        LOG.info("Criando nova placa de vídeo");
        return Response.status(Status.CREATED).entity(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response update(@PathParam("id") Long id, @Valid PlacaDeVideoRequestDTO dto) {
        LOG.infof("Atualizando placa de vídeo com id %d", id);
        placaDeVideoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando placa de vídeo com id %d", id);
        placaDeVideoService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({ "Adm" })
    @Path("/{idPlacaDeVideo}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("idPlacaDeVideo") Long id, @MultipartForm ImageForm form) {
        LOG.info("Execucao do uploadImage. Id do placaDeVideo: " + id);

        try {
            String nomeImagem = placaDeVideoFileService.save(form.getNomeImagem(), form.getImagem());

            placaDeVideoService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
            Response.status(500).build();
        }
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "Adm" })
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        LOG.info("Execucao do metodo downloadImage.");
        ResponseBuilder response = Response.ok(placaDeVideoFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }
}