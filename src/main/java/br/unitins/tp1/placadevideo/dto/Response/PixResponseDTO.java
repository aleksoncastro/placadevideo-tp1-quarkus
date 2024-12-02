package br.unitins.tp1.placadevideo.dto.response;

import java.math.BigDecimal;
import br.unitins.tp1.placadevideo.model.pagamento.Pix;

public record PixResponseDTO(
    Long id,    
    String chave,
    String codigo,
    BigDecimal valor
) {
    public static PixResponseDTO valueOf(Pix pix) {
        if (pix == null) {
            return null;
        }
        return new PixResponseDTO(
            pix.getId(),
            pix.getChave(),
            pix.getCodigo(),
            pix.getValor()
        );
    }
}
