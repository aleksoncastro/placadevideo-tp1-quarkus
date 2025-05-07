package br.unitins.tp1.placadevideo.model.placadevideo;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SaidaVideo extends DefaultEntity {
    private String tipo;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "placadevideo_id") // Essa coluna será usada como FK no banco
    private PlacaDeVideo placaDeVideo;

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

    public PlacaDeVideo getPlacaDeVideo() {
        return placaDeVideo;
    }

    public void setPlacaDeVideo(PlacaDeVideo placaDeVideo) {
        this.placaDeVideo = placaDeVideo;
    }

}
