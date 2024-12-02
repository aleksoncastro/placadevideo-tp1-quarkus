package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;

public record TelefoneClienteResponseDTO(
        Long id,
        String codigoArea,
        String numero) {

    public static TelefoneClienteResponseDTO valueOf(TelefoneCliente telefone) {
        return new TelefoneClienteResponseDTO(
        telefone.getId(), 
        telefone.getCodigoArea(), 
        telefone.getNumero());
    }

}
