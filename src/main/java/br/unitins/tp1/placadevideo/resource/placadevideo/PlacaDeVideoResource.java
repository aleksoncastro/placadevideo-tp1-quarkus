package br.unitins.tp1.placadevideo.resource.placadevideo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.placadevideo.dto.request.FiltroPlacaDeVideoDTO;
import br.unitins.tp1.placadevideo.dto.request.PaginacaoDTO;
import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.form.ImageForm;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.service.fileservice.FileService;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoFileServiceImpl;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(PlacaDeVideoResource.class);

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando placa de vídeo com id %d", id);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.findById(id))).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    @RolesAllowed({ "Adm", "User" })
    public Response findByDescricao(@PathParam("descricao") String descricao) {
        LOG.infof("Buscando placa de vídeo pela descrição: %s", descricao);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.findByDescricao(descricao))).build();
    }

    @GET
    @Path("/search/{modelo}")
    @RolesAllowed({ "Adm", "User" })
    public List<PlacaDeVideo> findByNome(@PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return placaDeVideoService.findByModelo(nome, page, pageSize);
    }

    @GET
    @RolesAllowed({ "Adm", "User" })
    public PaginacaoDTO findAll(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return placaDeVideoService.findAll(page, pageSize); // Agora retorna o DTO de paginação
    }

    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlacaDeVideoResponseDTO> findPage(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("20") int pageSize) {
        return placaDeVideoService.findPage(page, pageSize);
    }

    @GET
    @Path("/search/lancamentos/{prefixo1}/{prefixo2}/{valor1}/{valor2}")
    public Response findByLancamentos(
            @PathParam("prefixo1") String prefixo1,
            @PathParam("prefixo2") String prefixo2,
            @PathParam("valor1") String valor1,
            @PathParam("valor2") String valor2) {

        LOG.infof("Buscando últimos lançamentos com prefixos [%s, %s] e valores [%s, %s]", prefixo1, prefixo2, valor1,
                valor2);

        return Response.ok(
                placaDeVideoService.findByLancamentos(prefixo1, prefixo2, valor1, valor2)
                        .stream()
                        .map(PlacaDeVideoResponseDTO::valueOf)
                        .toList())
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
        PlacaDeVideo placaAtualizada = placaDeVideoService.update(id, dto);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaAtualizada)).build();
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
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form) {
        try {
            fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }
    }

    @GET
    @Path("/pedido/{id}")
    public Response findByPedido(@PathParam("id") Long idPedido){
        return Response.ok(placaDeVideoService.findByPedido(idPedido).stream().map(o -> PlacaDeVideoResponseDTO.valueOf(o)).toList())
        .build();
    }

    @PATCH
    @RolesAllowed({ "Adm" })
    @Path("/image/delete/{nomeImagem}/placa/{idPlaca}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response deleteImage(@PathParam("nomeImagem") String nomeImagem,
            @PathParam("idPlaca") Long idPlaca) {
        try {
            fileService.deletarImagem(idPlaca, nomeImagem);
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }

    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

    @GET
    @Path("/count")
    public long total() {
        return placaDeVideoService.count();
    }

    @GET
    @Path("/nome/{nome}/count")
    public long totalPorNome(String nome) {
        return placaDeVideoService.count(nome);
    }

    @POST
    @Path("/filtro")
    @RolesAllowed({ "Adm", "User" })
    public Response filtrar(@Valid FiltroPlacaDeVideoDTO filtro,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("20") int pageSize) {
        LOG.infof("Filtrando placas de vídeo com critérios: %s", filtro);

        List<PlacaDeVideoResponseDTO> resultado = placaDeVideoService.findByFiltros(filtro, page, pageSize);

        return Response.ok(resultado).build();
    }

    @GET
    @Path("/search")
    public Response searchPlacas(
            @QueryParam("q") String texto,
            @QueryParam("categoria") String categoria,
            @QueryParam("memoriaMin") Integer memoriaMin,
            @QueryParam("memoriaMax") Integer memoriaMax,
            @QueryParam("precoMin") BigDecimal precoMin,
            @QueryParam("precoMax") BigDecimal precoMax,
            @QueryParam("rayTracing") Boolean rayTracing,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize
    ) {
        // Aqui, monta o FiltroPlacaDeVideoDTO normalmente
        FiltroPlacaDeVideoDTO filtro = new FiltroPlacaDeVideoDTO(
                categoria, memoriaMin, memoriaMax, precoMin, precoMax, rayTracing
        );

        List<PlacaDeVideoResponseDTO> results = placaDeVideoService.findByTextoAndFiltros(texto, filtro, page, pageSize);

        return Response.ok(results).build();
    }
}
