package br.unitins.tp1.placadevideo.dto.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import br.unitins.tp1.placadevideo.model.pagamento.CartaoPagamento;

public record CartaoPagamentoResponseDTO(
    Long id,
    String numero,
    String titular,
    String dataValidade
) {
    public static CartaoPagamentoResponseDTO valueOf(CartaoPagamento cartao) {
        if (cartao == null) {
            return null;
        }
        return new CartaoPagamentoResponseDTO(
            cartao.getId(),
            ofuscarNumero(cartao.getNumero()),
            cartao.getTitular(),
            converterData(cartao.getDataValidade())
        );
    }

    private static String ofuscarNumero(String numero) {
        if (numero == null || numero.length() < 4) {
            return "****";
        }

        int tamanho = numero.length();
        StringBuilder ofuscado = new StringBuilder();
        for (int i = 0; i < tamanho - 4; i++) {
            if (i > 0 && i % 4 == 0) {
                ofuscado.append(" ");
            }
            ofuscado.append("*");
        }

        ofuscado.append(" ").append(numero.substring(tamanho - 4));
        return ofuscado.toString().trim();
    }

    private static String converterData(LocalDate validade) {
        if (validade == null) {
            return "Data inválida";
        }
        return validade.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }
}
