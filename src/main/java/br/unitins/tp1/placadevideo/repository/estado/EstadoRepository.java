package br.unitins.tp1.placadevideo.repository.estado;



import br.unitins.tp1.placadevideo.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public PanacheQuery<Estado> findByNome(String nome) {
        return find("SELECT estado FROM Estado estado Where estado.nome LIKE ?1", "%" + nome + "%");
    }

    public Estado findBySigla(String sigla) {
        return find("SELECT e FROM Estado e WHERE e.sigla = ?1", sigla).firstResult();
    }

}