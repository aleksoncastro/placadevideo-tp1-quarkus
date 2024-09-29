package br.unitins.tp1.placadevideo.dto;

public class MunicipioDTORequest {
    private String nome;
    private Long idEstado ;

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

}
