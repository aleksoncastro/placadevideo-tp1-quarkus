package br.unitins.tp1.placadevideo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Lote extends DefaultEntity {
    private String codigo;
    private Integer quantidade;
    private LocalDate dataFabricacao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

}
