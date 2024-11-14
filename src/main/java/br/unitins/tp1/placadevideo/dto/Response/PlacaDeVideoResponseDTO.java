package br.unitins.tp1.placadevideo.dto.Response;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.placadevideo.Fan;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;

public record PlacaDeVideoResponseDTO(
        Long id,
        String modelo,
        String categoria,
        Double preco,
        String resolucao,
        Integer energia,
        String descricao,
        Integer compatibilidade,
        Double clockBase,
        Double clockBoost,
        Fan fan,
        Boolean suporteRayTracing,
        MemoriaResponseDTO memoria,
        List<SaidaVideoResponseDTO> saidas,
        FornecedorResponseDTO fornecedor
) {

    public static PlacaDeVideoResponseDTO valueOf(PlacaDeVideo placaDeVideo) {
        return new PlacaDeVideoResponseDTO(
            placaDeVideo.getId(),
            placaDeVideo.getModelo(),
            placaDeVideo.getCategoria(),
            placaDeVideo.getPreco(),
            placaDeVideo.getResolucao(),
            placaDeVideo.getEnergia(),
            placaDeVideo.getDescricao(),
            placaDeVideo.getCompatibilidade(),
            placaDeVideo.getClockBase(), 
            placaDeVideo.getClockBoost(), 
            placaDeVideo.getFan(),
            placaDeVideo.getSuporteRayTracing(),
            MemoriaResponseDTO.valueOf(placaDeVideo.getMemoria()),
            placaDeVideo.getSaidas().stream()
                .map(SaidaVideoResponseDTO::valueOf)
                .collect(Collectors.toList()),
            FornecedorResponseDTO.valueOf(placaDeVideo.getFornecedor())
        );
    }
}