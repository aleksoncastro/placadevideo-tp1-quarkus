package br.unitins.tp1.placadevideo.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.usuario.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        UsuarioResponseDTO usuario,
        List<EnderecoResponseDTO> enderecos,
        List<TelefoneClienteResponseDTO> telefones,
        List<CartaoResponseDTO> cartoes) {

    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getDataNascimento(),
                UsuarioResponseDTO.valueOf(cliente.getUsuario()),
                cliente.getEnderecos().stream()
                        .map(EnderecoResponseDTO::valueOf)
                        .collect(Collectors.toList()),
                cliente.getTelefones().stream()
                        .map(TelefoneClienteResponseDTO::valueOf)
                        .collect(Collectors.toList()),
                        cliente.getCartoes() != null && !cliente.getCartoes().isEmpty()
                        ? cliente.getCartoes().stream()
                              .map(CartaoResponseDTO::valueOf)
                              .collect(Collectors.toList())
                        : null

        );
    }

}
