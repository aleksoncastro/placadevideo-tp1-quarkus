package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;

public record TelefoneFornecedorResponseDTO(
        Long id,
        String codigoArea,
        String numero) {

    public static TelefoneFornecedorResponseDTO valueOf(TelefoneFornecedor telefone) {
        return new TelefoneFornecedorResponseDTO(
        telefone.getId(), 
        telefone.getCodigoArea(), 
        telefone.getNumero());
    }

}
