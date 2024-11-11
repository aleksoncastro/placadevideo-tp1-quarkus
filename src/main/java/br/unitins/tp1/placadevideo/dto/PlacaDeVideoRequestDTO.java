package br.unitins.tp1.placadevideo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlacaDeVideoRequestDTO(
        @NotBlank(message = "O campo deve ser informado")
        String modelo,
        @NotBlank(message = "O campo deve ser informado")
        String categoria,
        @NotBlank(message = "O campo deve ser informado")
        Double preco,
        @NotBlank(message = "O campo deve ser informado")
        Integer vram,
        @NotBlank(message = "O campo deve ser informado")
        String resolucao,
        @NotBlank(message = "O campo deve ser informado")
        Integer energia,
        @NotBlank(message = "O campo deve ser informado")
        String descricao,
        @NotBlank(message = "O campo deve ser informado")
        Integer compatibilidade,
        @NotBlank(message = "O campo deve ser informado")
        Double clockBase,
        @NotBlank(message = "O campo deve ser informado")
        Double clockBoost,
        @NotBlank(message = "O campo deve ser informado")
        Integer idFan,
        @NotNull(message = "O campo deve ser informado")
        Long idFornecedor
    ) {
        
    }

