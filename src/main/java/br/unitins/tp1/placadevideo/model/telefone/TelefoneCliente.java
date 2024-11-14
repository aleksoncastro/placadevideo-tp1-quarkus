package br.unitins.tp1.placadevideo.model.telefone;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Entity;

@Entity
public class TelefoneCliente extends DefaultEntity {

    private String codigoArea;
    private String numero;

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
