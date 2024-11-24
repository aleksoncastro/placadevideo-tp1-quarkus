package br.unitins.tp1.placadevideo.dto.Response;

import java.math.BigDecimal;

import br.unitins.tp1.placadevideo.model.pedido.ItemPedido;

public record ItemPedidoResponsetDTO(
    Long idProduto,
    String nome, 
    Integer quantidade,
    BigDecimal preco) {
    
        public static ItemPedidoResponsetDTO valueOf(ItemPedido itemPedido){
            return new ItemPedidoResponsetDTO(
                itemPedido.getLote().getPlacaDeVideo().getId(), 
                itemPedido.getLote().getPlacaDeVideo().getModelo(),
                itemPedido.getQuantidade(), 
                itemPedido.getPreco());
        }

}
