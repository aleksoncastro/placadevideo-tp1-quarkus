package br.unitins.tp1.placadevideo.resource.placadevideo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.placadevideo.dto.request.PaginacaoDTO;
import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.form.ImageForm;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoFileServiceImpl;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
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

    private static final Logger LOG = Logger.getLogger(PlacaDeVideoResource.class);

    @GET
    @Path("/{id}")
    // @RolesAllowed("Adm")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando placa de vídeo com id %d", id);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.findById(id))).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    // @RolesAllowed({ "Adm", "User" })
    public Response findByDescricao(@PathParam("descricao") String descricao) {
        LOG.infof("Buscando placa de vídeo pela descrição: %s", descricao);
        return Response.ok(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.findByDescricao(descricao))).build();
    }

    @GET
    @Path("/search/{modelo}")
    // @RolesAllowed({ "Adm", "User" })
    public List<PlacaDeVideo> findByNome(@PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        return placaDeVideoService.findByModelo(nome, page, pageSize);
    }

    @GET
    // @RolesAllowed({ "Adm", "User" })
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
    // @RolesAllowed({ "Adm", "User" })
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
    // @RolesAllowed("Adm")
    public Response create(@Valid PlacaDeVideoRequestDTO dto) {
        LOG.info("Criando nova placa de vídeo");
        return Response.status(Status.CREATED).entity(PlacaDeVideoResponseDTO.valueOf(placaDeVideoService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    // @RolesAllowed("Adm")
    public Response update(@PathParam("id") Long id, @Valid PlacaDeVideoRequestDTO dto) {
        LOG.infof("Atualizando placa de vídeo com id %d", id);
        placaDeVideoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    // @RolesAllowed("Adm")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando placa de vídeo com id %d", id);
        placaDeVideoService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    // @RolesAllowed({ "Adm" })
    @Path("/{idPlacaDeVideo}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("idPlacaDeVideo") Long id, @MultipartForm ImageForm form) {
        LOG.info("Execução do uploadImage. Id da placaDeVideo: " + id);
        LOG.info("Nome do arquivo recebido para upload: " + form.getNomeImagem());

        try {
            String nomeImagem = placaDeVideoFileService.save(form.getNomeImagem(), form.getImagem());
            LOG.info("Arquivo salvo com sucesso. Novo nome: " + nomeImagem);
            placaDeVideoService.updateNomeImagem(id, nomeImagem);
            LOG.info("Nome da imagem atualizado no banco de dados.");
        } catch (IOException e) {
            LOG.error("Erro ao salvar o arquivo. Detalhes: " + e.getMessage(), e);
            return Response.status(500).entity("Erro ao salvar o arquivo.").build();
        }

        return Response.noContent().build();
    }

    @GET
    // @RolesAllowed({ "Adm" })
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        LOG.info("Execução do método downloadImage.");
        LOG.info("Nome da imagem solicitado para download: " + nomeImagem);

        try {
            // Busca o arquivo pelo nome
            File arquivo = placaDeVideoFileService.find(nomeImagem);
            LOG.info("Arquivo localizado com sucesso: " + arquivo.getAbsolutePath());

            // Retorna o arquivo como resposta
            ResponseBuilder response = Response.ok(arquivo);
            response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
            return response.build();
        } catch (ValidationException e) {
            LOG.error("Erro na validação: " + e.getMessage());
            return Response.status(404).entity("Arquivo não encontrado.").build();
        } catch (Exception e) {
            LOG.error("Erro ao realizar o download: " + e.getMessage(), e);
            return Response.status(500).entity("Erro interno no servidor.").build();
        }
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

    @GET
    @Path("/imagens/placasdevideo/{nome}")
    @Produces({ "image/jpeg", "image/png", "image/gif", "image/jpg" })
    public Response getImagem(@PathParam("nome") String nome) {
        // Caminho direto para a pasta onde você está salvando as imagens
        File imagem = new File("src/main/resources/META-INF/resources/placasdevideo_imagens/" + nome);

        // Verifica se o arquivo existe
        if (!imagem.exists()) {
            return Response.status(Status.NOT_FOUND).entity("Imagem não encontrada").build();
        }

        // Retorna o arquivo de imagem
        return Response.ok(imagem).build();
    }

}