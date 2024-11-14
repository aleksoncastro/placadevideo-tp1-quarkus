package br.unitins.tp1.placadevideo.model.usuario;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import br.unitins.tp1.placadevideo.model.Endereco;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends DefaultEntity{

@Column(length = 60, nullable = false )
private String nome;

@Column(length = 60, nullable = false, unique = true )
private String cpf;

private LocalDate dataNascimento;

private String email;

@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Endereco> enderecos;

@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
private List<TelefoneCliente> telefones;

@OneToOne
@JoinColumn(name = "id_usuario", unique = true)
private Usuario usuario;


public List<TelefoneCliente> getTelefones() {
    return telefones;
}

public void setTelefones(List<TelefoneCliente> telefones) {
    this.telefones = telefones;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public LocalDate getDataNascimento() {
    return dataNascimento;
}

public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public List<Endereco> getEnderecos() {
    return enderecos;
}

public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
}

public Usuario getUsuario() {
    return usuario;
}

public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}



}
