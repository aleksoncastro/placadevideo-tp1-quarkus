package br.unitins.tp1.placadevideo.dto;

import jakarta.validation.constraints.NotBlank;

public record PlacaDeVideoRequestDTO(
        @NotBlank(message = "O campo deve ser informado")
        String modelo,
        @NotBlank(message = "O campo deve ser informado")
        String categoria,
        @NotBlank(message = "O campo deve ser informado")
        double preco,
        @NotBlank(message = "O campo deve ser informado")
        int vram,
        @NotBlank(message = "O campo deve ser informado")
        String resolucao,
        @NotBlank(message = "O campo deve ser informado")
        int energia,
        @NotBlank(message = "O campo deve ser informado")
        String descricao,
        @NotBlank(message = "O campo deve ser informado")
        int compatibilidade
    ) {
        
    }

