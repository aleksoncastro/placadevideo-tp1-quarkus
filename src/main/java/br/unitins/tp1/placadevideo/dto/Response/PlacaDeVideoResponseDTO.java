package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.Fan;
import br.unitins.tp1.placadevideo.model.PlacaDeVideo;

public record PlacaDeVideoResponseDTO(
        Long id,
        String modelo,
        String categoria,
        Double preco,
        Integer vram,
        String resolucao,
        Integer energia,
        String descricao,
        Integer compatibilidade,
        Double clokBase,
        Double clokBoost,
        Fan fan
       // FornecedorResponseDTO fornecedor
        ) {

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
            placadevideo.getCompatibilidade(),
            placadevideo.getClokBase(),
            placadevideo.getClockBoost(),
            placadevideo.getFan()
           // FornecedorResponseDTO.valueOf(placadevideo.getFornecedor())
            
        );
    }
}
