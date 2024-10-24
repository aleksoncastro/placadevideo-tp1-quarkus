package br.unitins.tp1.placadevideo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlacaDeVideoRequestDTO(
        @NotNull(message = "O idLote não pode ser nulo")
        Long idLote,
        @NotBlank(message = "O campo deve ser informado")
        String modelo,
        @NotBlank(message = "O campo deve ser informado")
        String categoria,
        @NotBlank(message = "O campo deve ser informado")
        float preco,
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

