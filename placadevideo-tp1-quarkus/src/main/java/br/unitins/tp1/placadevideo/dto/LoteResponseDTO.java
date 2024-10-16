package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

import br.unitins.tp1.placadevideo.model.Lote;

public record LoteResponseDTO(
    Long id,
    String codigo,
    Integer quantidade,
    LocalDate dataFabricacao

) {
    public static LoteResponseDTO valueOf(Lote lote){
        return new LoteResponseDTO(
            lote.getId(),
            lote.getCodigo(),
            lote.getQuantidade(),
            lote.getDataFabricacao()
        );
    }
}
