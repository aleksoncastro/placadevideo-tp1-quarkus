package br.unitins.tp1.placadevideo.dto.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PlacaDeVideoRequestDTO(
    @NotBlank(message = "O modelo deve ser informado")
    String modelo,

    @NotBlank(message = "A categoria deve ser informada")
    String categoria,

    @NotNull(message = "O preço deve ser informado")
    @Positive(message = "O preço deve ser maior que zero")
    BigDecimal preco,

    @NotBlank(message = "A resolução deve ser informada")
    String resolucao,

    @NotNull(message = "A energia deve ser informada")
    @Positive(message = "A energia deve ser um valor positivo")
    Integer energia,

    @NotBlank(message = "A descrição deve ser informada")
    String descricao,

    @NotNull(message = "A barramento deve ser informada")
    String barramento,

    @NotNull(message = "O clock base deve ser informado")
    @Positive(message = "O clock base deve ser um valor positivo")
    Double clockBase,

    @NotNull(message = "O clock boost deve ser informado")
    @Positive(message = "O clock boost deve ser um valor positivo")
    Double clockBoost,

    @NotNull(message = "O id do fan deve ser informado")
    Integer idFan,

    @NotNull(message = "O fornecedor deve ser informado")
    Long idFornecedor,

    @NotNull(message = "O suporte ao Ray Tracing deve ser informado")
    Boolean suporteRayTracing,
        @Valid
        MemoriaRequestDTO memoria,
        @Valid
        TamanhoRequestDTO tamanho,
        @Valid
        List<SaidaVideoRequestDTO> saidas
    ) {
        
    }

