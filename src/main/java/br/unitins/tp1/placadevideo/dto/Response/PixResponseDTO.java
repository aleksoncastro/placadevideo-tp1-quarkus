package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.pagamento.Pix;

public record PixResponseDTO(
Long id,    
String chave,
String codigo
) {
    public static PixResponseDTO valueOf(Pix pix) {
        return new PixResponseDTO(
            pix.getId(),
            pix.getChave(),
            pix.getCodigo()
        );
    }
}
