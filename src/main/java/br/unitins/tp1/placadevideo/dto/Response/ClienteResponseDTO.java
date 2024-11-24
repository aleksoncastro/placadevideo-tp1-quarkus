package br.unitins.tp1.placadevideo.dto.Response;

import java.time.LocalDate;
import  java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.usuario.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String  cpf,
    LocalDate dataNascimento,
    String email, 
    UsuarioResponseDTO usuario,
    List<EnderecoResponseDTO> enderecos,
    List<TelefoneClienteResponseDTO> telefones
) {

    public static  ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(), 
            cliente.getCpf(),
            cliente.getDataNascimento(),
            cliente.getEmail(),
            UsuarioResponseDTO.valueOf(cliente.getUsuario()),
            cliente.getEnderecos().stream()
            .map(EnderecoResponseDTO::valueOf)
            .collect(Collectors.toList()),
            cliente.getTelefones().stream()
            .map(TelefoneClienteResponseDTO::valueOf)
            .collect(Collectors.toList())

        );
    }
    
}
