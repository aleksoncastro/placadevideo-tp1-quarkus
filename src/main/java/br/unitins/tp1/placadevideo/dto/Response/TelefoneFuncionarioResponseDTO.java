package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;

public record TelefoneFuncionarioResponseDTO(
        Long id,
        String codigoArea,
        String numero) {

    public static TelefoneFuncionarioResponseDTO valueOf(TelefoneFuncionario telefone) {
        return new TelefoneFuncionarioResponseDTO(
        telefone.getId(), 
        telefone.getCodigoArea(), 
        telefone.getNumero());
    }

}
