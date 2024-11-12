package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.ItemPedido;

public record ItemPedidoResponsetDTO(
    Long idProduto,
    String nome, 
    Integer quantidade,
    Double preco) {
    
        public static ItemPedidoResponsetDTO valueOf(ItemPedido itemPedido){
            return new ItemPedidoResponsetDTO(
                itemPedido.getLote().getPlacaDeVideo().getId(), 
                itemPedido.getLote().getPlacaDeVideo().getModelo(),
                itemPedido.getQuantidade(), 
                itemPedido.getPreco());
        }

}
