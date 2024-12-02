package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.pagamento.Boleto;
import br.unitins.tp1.placadevideo.model.pagamento.CartaoPagamento;
import br.unitins.tp1.placadevideo.model.pagamento.Pagamento;
import br.unitins.tp1.placadevideo.model.pagamento.Pix;

public record PagamentoResponseDTO(Object pagamento) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        if (pagamento instanceof Boleto boleto) {
            return new PagamentoResponseDTO(BoletoResponseDTO.valueOf(boleto));
        }
        if (pagamento instanceof Pix pix) {
            return new PagamentoResponseDTO(PixResponseDTO.valueOf(pix));
        }
        if (pagamento instanceof CartaoPagamento cartao) {
            return new PagamentoResponseDTO(CartaoPagamentoResponseDTO.valueOf(cartao));
        }
        return null;
    }
}
