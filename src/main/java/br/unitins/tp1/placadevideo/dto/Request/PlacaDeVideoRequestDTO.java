package br.unitins.tp1.placadevideo.dto.Request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlacaDeVideoRequestDTO(
        @NotBlank(message = "O campo deve ser informado")
        String modelo,
        @NotBlank(message = "O campo deve ser informado")
        String categoria,
        @NotBlank(message = "O campo deve ser informado")
        BigDecimal preco,
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
        Long idFornecedor,
        @NotNull(message = "O campo deve ser informado")
        Boolean suporteRayTracing,
        @NotNull(message = "O campo deve ser informado")
        MemoriaRequestDTO memoria,
        TamanhoRequestDTO tamanho,
        List<SaidaVideoRequestDTO> saidas
    ) {
        
    }

