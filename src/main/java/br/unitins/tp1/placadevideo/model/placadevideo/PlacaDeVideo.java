package br.unitins.tp1.placadevideo.model.placadevideo;

import java.util.List;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class PlacaDeVideo extends DefaultEntity {
    private String modelo;
    private String categoria;
    private Double preco;
    private String resolucao;
    private Integer energia;
    private String descricao;
    private Integer compatibilidade;
    private Fan fan;
    private Double clockBase;
    private Double clockBoost;
    private Boolean suporteRayTracing;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_memoria")
    private Memoria memoria;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_saidavideo")
    private List<SaidaVideo> saidas;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor; 
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    } 
    
    public Double getClockBase() {
        return clockBase;
    }

    public void setClockBase(Double clokBase) {
        this.clockBase = clokBase;
    }
    
    public Double getClockBoost() {
        return clockBoost;
    }

    public void setClockBoost(Double clockBoost) {
        this.clockBoost = clockBoost;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public Integer getEnergia() {
        return energia;
    }

    public void setEnergia(Integer energia) {
        this.energia = energia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCompatibilidade() {
        return compatibilidade;
    }

    public void setCompatibilidade(Integer compatibilidade) {
        this.compatibilidade = compatibilidade;
    }

    public Boolean getSuporteRayTracing() {
        return suporteRayTracing;
    }

    public void setSuporteRayTracing(Boolean suporteRayTracing) {
        this.suporteRayTracing = suporteRayTracing;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public List<SaidaVideo> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<SaidaVideo> saidas) {
        this.saidas = saidas;
    }



}
