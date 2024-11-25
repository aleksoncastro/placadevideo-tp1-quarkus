package br.unitins.tp1.placadevideo.repository.lote;

import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
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
        jpql.append("SELECT l ");
        jpql.append("FROM Lote l ");
        jpql.append("WHERE l.placaDeVideo.id = ?1 ");
        jpql.append("AND l.estoque > 0 ");
        jpql.append("ORDER BY l.dataFabricacao DESC ");

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
