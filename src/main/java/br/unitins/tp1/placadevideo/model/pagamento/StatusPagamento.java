package br.unitins.tp1.placadevideo.model.pagamento;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPagamento {
    PAGO(1, "PAGO"),
    PENDENTE(2, "PENDENTE"),
    CANCELADO(3, "CANCELADO");

    private final Integer id;
    private final String label;

    StatusPagamento(Integer id, String label){
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPagamento valueOf (Integer id) {
        if(id == null)
        return null;
        for(StatusPagamento statuspagamento : StatusPagamento.values()){
            if(statuspagamento.getId().equals(id))
            return statuspagamento;
        }
        
        throw new IllegalArgumentException("Id inválido");
    }

    

}
