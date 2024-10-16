package br.unitins.tp1.placadevideo.repository.pessoafiisica;

import java.util.List;

import br.unitins.tp1.placadevideo.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {
    
    public List<PessoaFisica> findByNome(String nome ){
        return find("SELECT p FROM PessoaFisica p Where p.nome LIKE ?1", "%"+ nome + "%").list();
    }

}
