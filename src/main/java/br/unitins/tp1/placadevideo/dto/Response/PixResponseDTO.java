package br.unitins.tp1.placadevideo.dto.Response;

import java.math.BigDecimal;

import br.unitins.tp1.placadevideo.model.pagamento.Pix;

public record PixResponseDTO(
Long id,    
String chave,
String codigo,
BigDecimal valor
) {
    public static PixResponseDTO valueOf(Pix pix) {
        return new PixResponseDTO(
            pix.getId(),
            pix.getChave(),
            pix.getCodigo(),
            pix.getValor()
        );
    }
}
