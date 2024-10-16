package br.unitins.tp1.placadevideo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Cartao extends DefaultEntity {
    @Column(length = 60, nullable = false, unique = true )
    private String numero;
    @Column(length = 60, nullable = false, unique = true )
    private String titular;
    @Column(unique = true)
    private LocalDate dataValidade;
    @Column(unique = true)
    private String cvv;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}
