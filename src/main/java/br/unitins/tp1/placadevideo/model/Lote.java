package br.unitins.tp1.placadevideo.model;

import java.time.LocalDate;

import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Lote extends DefaultEntity {
    private String codigo;
    private Integer estoque;
    @Column(name = "datafabricacao")
    private LocalDate dataFabricacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_placadevideo")
    private PlacaDeVideo placaDeVideo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public PlacaDeVideo getPlacaDeVideo() {
        return placaDeVideo;
    }

    public void setPlacaDeVideo(PlacaDeVideo placaDeVideo) {
        this.placaDeVideo = placaDeVideo;
    }

}
