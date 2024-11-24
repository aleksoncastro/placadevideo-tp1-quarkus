package br.unitins.tp1.placadevideo.model.usuario;

public enum StatusFuncionario {
    ATIVADO(1, "Ativado"),
    DESATIVADO(2, "Desativado");

    private final Integer id;
    private final String label;

    StatusFuncionario(Integer id, String label){
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusFuncionario valueOf (Integer id) {
        if(id == null)
        return null;

        for(StatusFuncionario statusFuncionario : StatusFuncionario.values()) {
            if(statusFuncionario.getId().equals(id))
            return statusFuncionario;
        }
        throw new IllegalArgumentException("Id inválido");
    }
}
