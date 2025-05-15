package br.unitins.tp1.placadevideo.model.placadevideo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class PlacaDeVideo extends DefaultEntity {
    private String modelo;
    private String categoria;
    private BigDecimal preco;
    private String resolucao;
    private Integer energia;
    private String descricao;
    private String barramento;
    private Fan fan;
    private Double clockBase;
    private Double clockBoost;
    private Boolean suporteRayTracing;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_memoria")
    private Memoria memoria;

    @OneToMany(mappedBy = "placaDeVideo", cascade = CascadeType.ALL, 
    orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SaidaVideo> saidas;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_tamanho")
    private Tamanho tamanho;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor; 
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "imagem_placadevideo", joinColumns = @JoinColumn(name = "id_placadevideo"))
    private List<String> listaImagem = new ArrayList<>();
    
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
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

    public String getBarramento() {
        return barramento;
    }

    public void setBarramento(String barramento) {
        this.barramento = barramento;
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

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public List<String> getListaImagem() {
        return listaImagem;
    }

    public void setListaImagem(List<String> listaImagem) {
        this.listaImagem = listaImagem;
    }



}
