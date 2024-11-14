package br.unitins.tp1.placadevideo.model;

import java.util.List;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Fornecedor extends DefaultEntity {
    private String nome;
    private String cnpj;
    private String email;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneFornecedor> telefones;


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

    public List<TelefoneFornecedor> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneFornecedor> telefones) {
        this.telefones = telefones;
    }

}
