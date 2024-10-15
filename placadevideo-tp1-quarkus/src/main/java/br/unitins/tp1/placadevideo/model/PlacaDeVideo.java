package br.unitins.tp1.placadevideo.model;

import jakarta.persistence.Entity;

@Entity
public class PlacaDeVideo extends DefaultEntity {
    private String modelo;
    private String categoria;
    private double preco;
    private int vram;
    private String resolucao;
    private int energia;
    private String descricao;
    private int compatibilidade;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getVram() {
        return vram;
    }

    public void setVram(int vram) {
        this.vram = vram;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCompatibilidade() {
        return compatibilidade;
    }

    public void setCompatibilidade(int compatibilidade) {
        this.compatibilidade = compatibilidade;
    }


}
