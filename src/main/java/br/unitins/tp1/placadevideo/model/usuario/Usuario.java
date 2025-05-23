package br.unitins.tp1.placadevideo.model.usuario;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

@Entity
public class Usuario extends DefaultEntity {
    @Column(unique = true)
    private String username;
    private String senha;
    private Perfil perfil;
    @Column(unique = true)
    private String email;
    @Column(length = 60, nullable = false, unique = true)
    private String cpf;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "imagem_usuario", joinColumns = @JoinColumn(name = "id_usuario"))
    private List<String> listaImagem = new ArrayList<>();
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getListaImagem() {
        return listaImagem;
    }

    public void setListaImagem(List<String> listaImagem) {
        this.listaImagem = listaImagem;
    }

}
