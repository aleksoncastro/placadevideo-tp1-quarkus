package br.unitins.tp1.placadevideo.resource.cliente;

import org.eclipse.microprofile.jwt.JsonWebToken;

import org.jboss.logging.Logger;

import br.unitins.tp1.placadevideo.dto.request.CartaoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.ClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.ClienteResponseDTO;
import br.unitins.tp1.placadevideo.service.cliente.ClienteService;
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
import jakarta.ws.rs.core.Response.Status;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    public ClienteService clienteService;

    @Inject
    public JsonWebToken jsonWebToken;

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(ClienteResponseDTO.valueOf(clienteService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(clienteService.findByNome(nome).stream().map(o -> ClienteResponseDTO.valueOf(o)).toList())
                .build();
    }

    @GET
    @Path("/search/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf) {
        return Response.ok(ClienteResponseDTO.valueOf(clienteService.findByCpf(cpf))).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(clienteService.findAll().stream().map(o -> ClienteResponseDTO.valueOf(o)).toList()).build();
    }

    @POST
    public Response create(ClienteRequestDTO dto) {
        return Response.status(Status.CREATED).entity(ClienteResponseDTO.valueOf(clienteService.create(dto))).build();
    }

    @POST
    @Path("/{id}/enderecos")
    public Response addEndereco(@PathParam("id") Long clienteId, EnderecoRequestDTO enderecoDTO) {
        clienteService.addEndereco(clienteId, enderecoDTO);
        return Response.status(Status.CREATED).build();
    }

    @POST
    @Path("/{id}/telefones")
    public Response addTelefone(@PathParam("id") Long clienteId, TelefoneClienteRequestDTO telefoneDTO) {
        clienteService.addTelefone(clienteId, telefoneDTO);
        return Response.status(Status.CREATED).build();
    }

    @POST
    @Path("/{id}/cartoes")
    public Response addCartao(@PathParam("id") Long clienteId, CartaoRequestDTO cartaoDTO) {
        clienteService.addCartao(clienteId, cartaoDTO);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}/endereco/{enderecoId}/telefone/{telefoneId}")
    public Response update(
            @PathParam("id") Long id,
            @PathParam("enderecoId") Long enderecoId,
            @PathParam("telefoneId") Long telefoneId,
            @Valid ClienteRequestDTO dto) {
        clienteService.update(id, enderecoId, telefoneId, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "User" })
    @Path("/desejos")
    public Response getListaDesejos() {
        String username = jsonWebToken.getSubject();
        LOG.info("Execucao do metodo getListaDesejos");
        return Response.ok(clienteService.getListaDesejos(username)).build();
    }

    @PATCH
    @RolesAllowed({ "User" })
    @Path("/desejos/adicionar/{idProduto}")
    public Response adicionarProdutoListaDesejo(@PathParam("idProduto") Long idProduto) {
        String username = jsonWebToken.getSubject();
        LOG.info("Execucao do metodo adicionarProdutoListaDesejo");
        clienteService.adicionarProdutoListaDesejo(username, idProduto);
        return Response.noContent().build();
    }

    @PATCH
    @RolesAllowed({ "User" })
    @Path("/desejos/remover/{idProduto}")
    public Response removerProdutoListaDesejo(@PathParam("idProduto") Long idProduto) {
        String username = jsonWebToken.getSubject();
        LOG.info("Execucao do metodo removerProdutoListaDesejo");
        clienteService.removerProdutoListaDesejo(username, idProduto);
        return Response.noContent().build();
    }

}