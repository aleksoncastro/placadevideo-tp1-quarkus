package br.unitins.tp1.placadevideo.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {
    AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento"),
    PROCESSANDO(2, "Processando"),
    ENVIADO(3, "Enviado"),
    ENTREGUE(4, "Entregue"),
    CANCELADO(5, "Cancelado");

    private final Integer id;
    private final String label;

    StatusPedido(Integer id, String label){
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPedido valueOf (Integer id) {
        if(id == null)
        return null;

        for(StatusPedido statusPedido : StatusPedido.values()) {
            if(statusPedido.getId().equals(id))
            return statusPedido;
        }
        throw new IllegalArgumentException("Id inválido");
    }

    

}