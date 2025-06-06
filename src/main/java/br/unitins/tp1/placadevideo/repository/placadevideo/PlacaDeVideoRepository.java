package br.unitins.tp1.placadevideo.repository.placadevideo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlacaDeVideoRepository implements PanacheRepository<PlacaDeVideo> {

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
        PanacheQuery<PlacaDeVideo> query1 = find(
                "SELECT p FROM PlacaDeVideo p WHERE p.modelo LIKE ?1 AND p.modelo LIKE ?2", prefixo1 + "%",
                "%" + valor1 + "%");
        PanacheQuery<PlacaDeVideo> query2 = find(
                "SELECT p FROM PlacaDeVideo p WHERE p.modelo LIKE ?1 AND p.modelo LIKE ?2", prefixo2 + "%",
                "%" + valor2 + "%");

        List<PlacaDeVideo> resultados1 = query1.range(0, 9).list();
        List<PlacaDeVideo> resultados2 = query2.range(0, 9).list();

        // Combina os dois conjuntos de resultados
        List<PlacaDeVideo> resultados = new ArrayList<>();
        resultados.addAll(resultados1);
        resultados.addAll(resultados2);

        return resultados;
    }

    public List<PlacaDeVideo> findPlacasByPedidoId(Long pedidoId) {
        String jpql = """
                    SELECT DISTINCT l.placaDeVideo
                    FROM Pedido p
                    JOIN p.listaItemPedido item
                    JOIN item.lote l
                    WHERE p.id = ?1
                """;

        return getEntityManager()
                .createQuery(jpql, PlacaDeVideo.class)
                .setParameter(1, pedidoId)
                .getResultList();
    }

    public PanacheQuery<PlacaDeVideo> findByTexto(String texto) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT ");
        jpql.append("  p ");
        jpql.append("FROM ");
        jpql.append("  PlacaDeVideo p ");
        jpql.append("WHERE ");
        jpql.append("  LOWER(p.modelo) LIKE LOWER(?1) ");
        jpql.append("  OR LOWER(p.fornecedor.nome) LIKE LOWER(?1) ");
        jpql.append("  OR LOWER(p.categoria) LIKE LOWER(?1) ");
        jpql.append("  OR LOWER(p.descricao) LIKE LOWER(?1) ");

        return find(jpql.toString(), "%" + texto + "%");
    }

    public PanacheQuery<PlacaDeVideo> findByFiltros(
            String categoria,
            Integer memoriaMin,
            Integer memoriaMax,
            BigDecimal precoMin,
            BigDecimal precoMax,
            Boolean rayTracing) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM PlacaDeVideo p WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (categoria != null) {
            jpql.append("AND p.categoria = :categoria ");
            params.put("categoria", categoria);
        }
        if (memoriaMin != null) {
            jpql.append("AND p.memoria.capacidade >= :memoriaMin ");
            params.put("memoriaMin", memoriaMin);
        }
        if (memoriaMax != null) {
            jpql.append("AND p.memoria.capacidade <= :memoriaMax ");
            params.put("memoriaMax", memoriaMax);
        }
        if (precoMin != null) {
            jpql.append("AND p.preco >= :precoMin ");
            params.put("precoMin", precoMin);
        }
        if (precoMax != null) {
            jpql.append("AND p.preco <= :precoMax ");
            params.put("precoMax", precoMax);
        }
        if (rayTracing != null) {
            jpql.append("AND p.suporteRayTracing = :rayTracing ");
            params.put("rayTracing", rayTracing);
        }

        return find(jpql.toString(), params);
    }

    public PanacheQuery<PlacaDeVideo> findByTextoAndFiltros(
            String texto,
            String categoria,
            Integer memoriaMin,
            Integer memoriaMax,
            BigDecimal precoMin,
            BigDecimal precoMax,
            Boolean rayTracing) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM PlacaDeVideo p WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (texto != null && !texto.isEmpty()) {
            String[] termos = texto.trim().toLowerCase().split("\\s+");
            for (int i = 0; i < termos.length; i++) {
                String termo = termos[i];
                jpql.append("AND (");
                jpql.append("LOWER(p.modelo) LIKE :texto").append(i);
                jpql.append(" OR LOWER(p.categoria) LIKE :texto").append(i);
                jpql.append(" OR LOWER(p.descricao) LIKE :texto").append(i);
                jpql.append(" OR LOWER(p.fornecedor.nome) LIKE :texto").append(i);
                jpql.append(") ");
                params.put("texto" + i, "%" + termo + "%");
            }
        }

        if (categoria != null) {
            jpql.append("AND p.categoria = :categoria ");
            params.put("categoria", categoria);
        }
        if (memoriaMin != null) {
            jpql.append("AND p.memoria.capacidade >= :memoriaMin ");
            params.put("memoriaMin", memoriaMin);
        }
        if (memoriaMax != null) {
            jpql.append("AND p.memoria.capacidade <= :memoriaMax ");
            params.put("memoriaMax", memoriaMax);
        }
        if (precoMin != null) {
            jpql.append("AND p.preco >= :precoMin ");
            params.put("precoMin", precoMin);
        }
        if (precoMax != null) {
            jpql.append("AND p.preco <= :precoMax ");
            params.put("precoMax", precoMax);
        }
        if (rayTracing != null) {
            jpql.append("AND p.suporteRayTracing = :rayTracing ");
            params.put("rayTracing", rayTracing);
        }

        return find(jpql.toString(), params);
    }
}
