package br.unitins.tp1.placadevideo.dto.Request;

import java.math.BigDecimal;

public record ItemPedidoRequestDTO(
    Long idProduto, 
    Integer quantidade,
    BigDecimal preco) {
    
}
