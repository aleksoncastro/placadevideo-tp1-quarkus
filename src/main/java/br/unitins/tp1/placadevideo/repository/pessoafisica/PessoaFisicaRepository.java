package br.unitins.tp1.placadevideo.repository.pessoafisica;

import java.util.List;

import br.unitins.tp1.placadevideo.model.usuario.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {
    
    public List<PessoaFisica> findByNome(String nome ){
        return find("SELECT p FROM PessoaFisica p Where p.nome LIKE ?1", "%"+ nome + "%").list();
    }

    public PessoaFisica findByCpf(String cpf) {
        return find("SELECT p FROM PessoaFisica p WHERE p.cpf = ?1",  cpf ).firstResult();
    }

}
