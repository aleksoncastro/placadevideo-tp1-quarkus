package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;
import  java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String  cpf,
    LocalDate dataNascimento,
    String email, 
    List<EnderecoResponseDTO> enderecos,
    List<TelefoneResponseDTO> telefones
) {

    public static  ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(), 
            cliente.getCpf(),
            cliente.getDataNascimento(),
            cliente.getEmail(),
            cliente.getEnderecos().stream()
            .map(EnderecoResponseDTO::valueOf)
            .collect(Collectors.toList()),
            cliente.getTelefones().stream()
            .map(TelefoneResponseDTO::valueOf)
            .collect(Collectors.toList())

        );
    }
    
}
