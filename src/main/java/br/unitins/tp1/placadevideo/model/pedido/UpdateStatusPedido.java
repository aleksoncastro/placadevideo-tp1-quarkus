package br.unitins.tp1.placadevideo.model.pedido;

import java.time.LocalDateTime;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Entity;

@Entity
public class UpdateStatusPedido extends DefaultEntity{
    private StatusPedido status;
    private LocalDateTime dataAtualizacao;
    
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    
}
