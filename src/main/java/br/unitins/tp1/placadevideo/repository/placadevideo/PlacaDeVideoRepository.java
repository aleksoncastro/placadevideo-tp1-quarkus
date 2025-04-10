package br.unitins.tp1.placadevideo.repository.placadevideo;


import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlacaDeVideoRepository implements PanacheRepository<PlacaDeVideo>{
    
    public PanacheQuery<PlacaDeVideo> findByModelo(String modelo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT ");
        jpql.append("  p ");
        jpql.append("FROM ");
        jpql.append("  PlacaDeVideo p ");
        jpql.append("WHERE ");
        jpql.append("  p.modelo LIKE ?1 ");
        return find(jpql.toString(), "%" + modelo + "%");
    }
    
    public PlacaDeVideo findByDescricao(String descricao) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT ");
        jpql.append("  p ");
        jpql.append("FROM ");
        jpql.append("  PlacaDeVideo p ");
        jpql.append("WHERE ");
        jpql.append("  p.descricao = ?1 ");
        return find(jpql.toString(), descricao).firstResult();
    }
}
