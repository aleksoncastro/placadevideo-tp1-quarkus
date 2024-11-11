package br.unitins.tp1.placadevideo.repository.lote;

import br.unitins.tp1.placadevideo.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {
    /**
     * @return retorna a placa com o lote mais antigo e com quantidade maior que
     *         zero
     */

    public Lote findByIdPlacaDeVideo(Long idPlaca) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT  ");
        jpql.append("l ");
        jpql.append("FROM  ");
        jpql.append(" Lote  ");
        jpql.append("Where ");
        jpql.append(" l.placadevideo.id LIKE ?1 ");
        jpql.append("AND l.quantidade > 0  ");
        jpql.append(" ORDER BY l.datafabricacao DESC  ");

        return find(jpql.toString(), idPlaca).firstResult();
    }

    public Lote findByCodigo(String codigo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT l ");
        jpql.append("FROM Lote l ");
        jpql.append("WHERE l.codigo = ?1");

        return find(jpql.toString(), codigo).firstResult();
    }

}
