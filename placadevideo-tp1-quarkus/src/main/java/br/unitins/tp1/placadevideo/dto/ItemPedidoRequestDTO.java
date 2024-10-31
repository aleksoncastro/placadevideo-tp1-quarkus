package br.unitins.tp1.placadevideo.dto;

public record ItemPedidoRequestDTO(
    Long idProduto, 
    Integer quantidade,
    Double preco) {
    
}
