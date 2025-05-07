package br.unitins.tp1.placadevideo.repository.placadevideo;


import java.util.ArrayList;
import java.util.List;

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

    public List<PlacaDeVideo> findByUltimosLancamentos(String prefixo1, String prefixo2, String valor1, String valor2) {
    // Para limitar 10 resultados por conjunto de filtros
    PanacheQuery<PlacaDeVideo> query1 = find("SELECT p FROM PlacaDeVideo p WHERE p.modelo LIKE ?1 AND p.modelo LIKE ?2", prefixo1 + "%", "%" + valor1 + "%");
    PanacheQuery<PlacaDeVideo> query2 = find("SELECT p FROM PlacaDeVideo p WHERE p.modelo LIKE ?1 AND p.modelo LIKE ?2", prefixo2 + "%", "%" + valor2 + "%");
    
    List<PlacaDeVideo> resultados1 = query1.range(0, 9).list();
    List<PlacaDeVideo> resultados2 = query2.range(0, 9).list();

    // Combina os dois conjuntos de resultados
    List<PlacaDeVideo> resultados = new ArrayList<>();
    resultados.addAll(resultados1);
    resultados.addAll(resultados2);

    return resultados;
}
    
}
