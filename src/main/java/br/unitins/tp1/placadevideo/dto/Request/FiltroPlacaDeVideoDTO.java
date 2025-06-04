package br.unitins.tp1.placadevideo.dto.request;

import java.math.BigDecimal;

public record FiltroPlacaDeVideoDTO(
    String categoria,
    Integer memoriaMin,
    Integer memoriaMax,
    BigDecimal precoMin,
    BigDecimal precoMax,
    Boolean rayTracing
) {}
