package br.unitins.tp1.placadevideo.repository.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.response.PedidoGeralResponseDTO;
import br.unitins.tp1.placadevideo.model.pedido.Pedido;
import br.unitins.tp1.placadevideo.model.pedido.StatusPedido;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByCliente(Long idCliente) {
        return find("SELECT p FROM Pedido p WHERE p.cliente.id =?1", idCliente).list();
    }

    public PanacheQuery<Pedido> findByPedidoUsername(String username) {
        return find("SELECT p FROM Pedido p WHERE p.usuario.username =?1", username);
    }

    public List<Pedido> findByUsername(String username) {
        return find("SELECT p FROM Pedido p WHERE p.usuario.username =?1", username).list();
    }

    public List<Pedido> findByItem(Long idPlacaDeVideo) {
        return find(
                "SELECT DISTINCT p FROM Pedido p JOIN p.listaItemPedido ip JOIN ip.lote l JOIN l.placaDeVideo pdv WHERE pdv.id = ?1",
                idPlacaDeVideo).list();

    }

    public List<Pedido> findByStatus(int idStatus) {
        return find("SELECT p FROM Pedido p JOIN p.listaStatus l WHERE l.status = ?1", StatusPedido.valueOf(idStatus))
                .list();
    }

    public List<Pedido> findPedidoPagamentoNull() {
        return find("SELECT p FROM Pedido p WHERE p.pagamento IS NULL").list();
    }

    public PanacheQuery<Pedido> buscarComClienteEPedido(int page, int pageSize) {
        return find("SELECT p FROM Pedido p LEFT JOIN FETCH p.cliente LEFT JOIN FETCH p.cliente.usuario")
                .page(page, pageSize);
    }

}
