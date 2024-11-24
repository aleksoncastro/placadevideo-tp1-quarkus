package br.unitins.tp1.placadevideo.model.pedido;

import java.math.BigDecimal;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import br.unitins.tp1.placadevideo.model.Lote;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;
    private Integer quantidade;
    private BigDecimal preco;
    
    public Lote getLote() {
        return lote;
    }
    public void setLote(Lote lote) {
        this.lote = lote;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    
    
}
