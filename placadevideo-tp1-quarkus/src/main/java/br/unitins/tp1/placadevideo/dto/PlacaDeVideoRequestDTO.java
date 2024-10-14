package br.unitins.tp1.placadevideo.dto;

public record PlacaDeVideoRequestDTO(
        String modelo,
        String categoria,
        double preco,
        int vram,
        String resolucao,
        int energia,
        String descricao,
        int compatibilidade
    ) {
        
    }

