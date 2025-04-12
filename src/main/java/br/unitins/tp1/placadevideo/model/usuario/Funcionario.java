package br.unitins.tp1.placadevideo.model.usuario;
import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Funcionario extends DefaultEntity{

@Column(length = 60, nullable = false )
private String nome;

private LocalDate dataNascimento;

private Double salario;

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
private List<TelefoneFuncionario> telefones;

@OneToOne
@JoinColumn(name = "id_usuario", unique = true)
private Usuario usuario;

private StatusFuncionario statusFuncionario;



public List<TelefoneFuncionario> getTelefones() {
    return telefones;
}

public void setTelefones(List<TelefoneFuncionario> telefones) {
    this.telefones = telefones;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}


public LocalDate getDataNascimento() {
    return dataNascimento;
}

public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
}

public Usuario getUsuario() {
    return usuario;
}

public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}

public Double getSalario() {
    return salario;
}

public void setSalario(Double salario) {
    this.salario = salario;


}
public StatusFuncionario getStatusFuncionario() {
    return statusFuncionario;

}
public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
    this.statusFuncionario = statusFuncionario;
}


}