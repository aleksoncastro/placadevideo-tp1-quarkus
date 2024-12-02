package br.unitins.tp1.placadevideo.dto.response;

import java.math.BigDecimal;
import br.unitins.tp1.placadevideo.model.pagamento.Boleto;

public record BoletoResponseDTO(
    Long id,    
    String chave,
    BigDecimal valor
) {
    public static BoletoResponseDTO valueOf(Boleto boleto) {
        if (boleto == null) {
            return null;
        }
        return new BoletoResponseDTO(
            boleto.getId(),
            boleto.getCodigo(),
            boleto.getValor()
        );
    }
}
