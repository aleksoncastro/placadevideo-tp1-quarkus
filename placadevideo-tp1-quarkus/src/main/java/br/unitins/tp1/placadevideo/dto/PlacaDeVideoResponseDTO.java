package br.unitins.tp1.placadevideo.dto;

import br.unitins.tp1.placadevideo.model.PlacaDeVideo;

public record PlacaDeVideoResponseDTO(
        Long id,
        String modelo,
        String categoria,
        double preco,
        int vram,
        String resolucao,
        int energia,
        String descricao,
        int compatibilidade) {

    public static PlacaDeVideoResponseDTO valueOf(PlacaDeVideo placadevideo){
        return new PlacaDeVideoResponseDTO(
            placadevideo.getId(),
            placadevideo.getModelo(),
            placadevideo.getCategoria(),
            placadevideo.getPreco(),
            placadevideo.getVram(),
            placadevideo.getResolucao(),
            placadevideo.getEnergia(),
            placadevideo.getDescricao(),
            placadevideo.getCompatibilidade()
        );
    }
}
