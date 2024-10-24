package br.unitins.tp1.placadevideo.repository.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {
    
    public List<Lote> findByNome(String nome ){
        return find("SELECT cliente FROM Cliente cliente Where cliente.nome LIKE ?1", "%"+ nome + "%").list();
    }

    public Lote findByCodigo(String codigo) {
        return find("SELECT l FROM Lote l WHERE l.codigo = ?1",  codigo ).firstResult();
    }

}
