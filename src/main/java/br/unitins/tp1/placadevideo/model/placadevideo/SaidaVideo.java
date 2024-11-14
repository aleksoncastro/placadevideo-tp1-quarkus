package br.unitins.tp1.placadevideo.model.placadevideo;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Entity;

@Entity
public class SaidaVideo extends DefaultEntity {
    private String tipo;
    private Integer quantidade;
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
}
