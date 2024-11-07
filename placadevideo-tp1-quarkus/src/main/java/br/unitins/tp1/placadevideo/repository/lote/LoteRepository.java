package br.unitins.tp1.placadevideo.repository.lote;

import br.unitins.tp1.placadevideo.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {
    /**
     * @return  retorna a placa com o lote mais antigo e com quantidade maior que zero
     */

    public Lote findByIdPlacaDeVideo(Long idPlaca ){
        StringBuffer jpql = null;
        jpql.append("SELECT  ");
        jpql.append("l ");
        jpql.append("FROM  ");
        jpql.append(" Lote  ");
        jpql.append("Where ");
        jpql.append(" l.placadevideo.id LIKE ?1 ");
        jpql.append("AND l.quantidade > 0  ");
        jpql.append(" ORDER BY l.datafabricacao DESC  ");
        
        return find("SELECT l FROM Lote l Where l.placadevideo.id LIKE ?1 AND l.quantidade > 0 ORDER BY l.datafabricacao ", idPlaca  ).firstResult();
    }

    public Lote findByCodigo(String codigo) {
        return find("SELECT l FROM Lote l WHERE l.codigo = ?1", codigo ).firstResult();
    }

}
