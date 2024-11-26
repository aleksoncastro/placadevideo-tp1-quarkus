package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.pedido.EnderecoEntrega;

public record EnderecoEntregaResponseDTO(
    Long id,
    String cep,
    String cidade,
    String estado,
    String bairro,
    String rua, 
    String numero
) {
    public static EnderecoEntregaResponseDTO valueOf(EnderecoEntrega endereco){
        return new EnderecoEntregaResponseDTO(
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
