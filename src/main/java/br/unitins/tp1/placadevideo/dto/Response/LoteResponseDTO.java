package br.unitins.tp1.placadevideo.dto.Response;

import java.time.LocalDate;

import br.unitins.tp1.placadevideo.model.Lote;

public record LoteResponseDTO(
    Long id,
    String codigo,
    Integer estoque,
    LocalDate dataFabricacao,
    PlacaDeVideoResponseDTO placaDeVideo

) {
    public static LoteResponseDTO valueOf(Lote lote){
        return new LoteResponseDTO(
            lote.getId(),
            lote.getCodigo(),
            lote.getEstoque(),
            lote.getDataFabricacao(),
            PlacaDeVideoResponseDTO.valueOf(lote.getPlacaDeVideo())
        );
    }
}
