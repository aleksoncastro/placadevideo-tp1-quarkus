package br.unitins.tp1.placadevideo.model;

import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TelefoneCliente extends DefaultEntity {

    private String codigoArea;
    private String numero;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private  Cliente cliente;
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

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
