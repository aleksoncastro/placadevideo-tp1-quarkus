package br.unitins.tp1.placadevideo.model.placadevideo;


import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Entity;

@Entity
public class Memoria extends DefaultEntity {
    private String tipoMemoria;
    private Integer capacidade;//GB
    private Integer larguraBanda;//bits
    private Integer velocidadeMemoria;//GBps
    
    public String getTipoMemoria() {
        return tipoMemoria;
    }
    public void setTipoMemoria(String tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
    }
    public Integer getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
    public Integer getLarguraBanda() {
        return larguraBanda;
    }
    public void setLarguraBanda(Integer larguraBanda) {
        this.larguraBanda = larguraBanda;
    }
    public Integer getVelocidadeMemoria() {
        return velocidadeMemoria;
    }
    public void setVelocidadeMemoria(Integer velocidadeMemoria) {
        this.velocidadeMemoria = velocidadeMemoria;
    }

    

}