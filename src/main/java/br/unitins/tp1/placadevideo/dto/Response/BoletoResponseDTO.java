package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.pagamento.Boleto;

public record BoletoResponseDTO(
    Long id,     
String codigo
) {
    public static BoletoResponseDTO valueOf(Boleto boleto) {
        return new BoletoResponseDTO(
        boleto.getId(),    
        boleto.getCodigo()
        );
    }
}