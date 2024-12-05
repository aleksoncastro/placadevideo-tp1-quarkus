package br.unitins.tp1.placadevideo.model.usuario;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.placadevideo.model.DefaultEntity;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;

    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneCliente> telefones;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Cartao> cartoes;

    @ManyToMany
    @JoinTable(name = "lista_desejo", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_placadevideo"))
    private List<PlacaDeVideo> listaDesejos;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public List<PlacaDeVideo> getListaDesejos() {
        return listaDesejos;
    }

    public void setListaDesejos(List<PlacaDeVideo> listaDesejos) {
        this.listaDesejos = listaDesejos;
    }


}
