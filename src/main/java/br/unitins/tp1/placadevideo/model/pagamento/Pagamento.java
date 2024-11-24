package br.unitins.tp1.placadevideo.model.pagamento;

import java.math.BigDecimal;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {
    @Column(nullable = false)
    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    } 

}
