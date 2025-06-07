package br.unitins.tp1.placadevideo.dto.response;

import java.math.BigDecimal;

import br.unitins.tp1.placadevideo.model.pedido.ItemPedido;

public record ItemPedidoResponsetDTO(
    Long idProduto,
    String nome, 
    LoteResponseDTO lote,
    Integer quantidade,
    BigDecimal preco) {
    
        public static ItemPedidoResponsetDTO valueOf(ItemPedido itemPedido){
            return new ItemPedidoResponsetDTO(
                itemPedido.getLote().getPlacaDeVideo().getId(), 
                itemPedido.getLote().getPlacaDeVideo().getModelo(),
                LoteResponseDTO.valueOf(itemPedido.getLote()),
                itemPedido.getQuantidade(), 
                itemPedido.getPreco());
        }

}
