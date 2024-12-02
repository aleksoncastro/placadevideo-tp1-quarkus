package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.usuario.Endereco;

public record EnderecoResponseDTO(
    Long id,
    String cep,
    String cidade,
    String estado,
    String bairro,
    String rua, 
    String numero
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco){
        return new EnderecoResponseDTO(
            endereco.getId(),
            endereco.getCep(),
            endereco.getCidade(),
            endereco.getEstado(),
            endereco.getBairro(),
            endereco.getRua(),
            endereco.getNumero()
        );
    }
}
