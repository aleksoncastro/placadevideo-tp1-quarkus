package br.unitins.tp1.placadevideo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
@Entity
public class Fornecedor extends DefaultEntity{
    @Column(length = 60, nullable = false, unique = true)
    private  String nome;

    @Column(length = 60, nullable = false, unique = true)
    private String cnpj;

    @Column(length = 60, nullable = false )
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "fabricante_endereco", joinColumns = @JoinColumn(name = "id_fabricante"), inverseJoinColumns = @JoinColumn(name = "id_endereco", unique = true))
    private List<Endereco> enderecos;
    
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    
}
