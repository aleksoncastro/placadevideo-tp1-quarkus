package br.unitins.tp1.placadevideo.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Fornecedor extends DefaultEntity{

@Column(length = 60, nullable = false )
private String nome;

@Column(length = 60, nullable = false )
private String cnpj;

private String email;

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
@JoinTable(name = "fornecedor_telefone", joinColumns = @JoinColumn(name = "id_fornecedor"), inverseJoinColumns = @JoinColumn(name = "id_telefone", unique = true))
private List<TelefoneFornecedor> telefones;



public String getCnpj() {
    return cnpj;
}

public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
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
