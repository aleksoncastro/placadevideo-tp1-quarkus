package br.unitins.tp1.placadevideo.dto;

import br.unitins.tp1.placadevideo.model.Endereco;

public record EnderecoResponseDTO(
    Long id,
    String cep,
    String cidade,
    String estado,
    String bairro, 
    String numero,
    Long idCliente
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco){
        return new EnderecoResponseDTO(
            endereco.getId(),
            endereco.getCep(),
            endereco.getCidade(),
            endereco.getEstado(),
            endereco.getBairro(),
            endereco.getNumero(),
            endereco.getIdCliente()
        );
    }
}
