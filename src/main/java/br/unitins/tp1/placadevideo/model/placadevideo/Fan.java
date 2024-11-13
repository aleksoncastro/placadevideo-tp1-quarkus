package br.unitins.tp1.placadevideo.model.placadevideo;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Fan {
    ONE(1,"One"),
    DOUBLE(2, "Double"),
    TRIPLE(3, "Triple");

    private Integer id;
    
    private String label;
    
    Fan(Integer id, String label){
        this.id = id;
        this.label = label;
    }
    
    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Fan valueOf(Integer id){
        if(id == null)
        return null;

        for(Fan fan: Fan.values()){
            if(fan.id.equals(id))
            return fan;
        }
        throw new IllegalArgumentException("Id inválido");
    }
    
}
