package br.unitins.tp1.placadevideo.model.placadevideo;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Entity;

@Entity
public class Tamanho extends DefaultEntity {
    private Integer altura;
    private Integer largura;
    private Integer comprimento;
    
    public Integer getAltura() {
        return altura;
    }
    public void setAltura(Integer altura) {
        this.altura = altura;
    }
    public Integer getLargura() {
        return largura;
    }
    public void setLargura(Integer largura) {
        this.largura = largura;
    }
    public Integer getComprimento() {
        return comprimento;
    }
    public void setComprimento(Integer comprimento) {
        this.comprimento = comprimento;
    }

    
}
