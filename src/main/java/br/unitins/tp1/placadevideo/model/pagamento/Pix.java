package br.unitins.tp1.placadevideo.model.pagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {
    @Column(nullable = false)
    private String chave;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    

}
