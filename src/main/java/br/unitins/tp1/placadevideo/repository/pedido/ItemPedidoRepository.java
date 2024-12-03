package br.unitins.tp1.placadevideo.repository.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.model.pedido.ItemPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {

    public List<ItemPedido> findByPedidoId(Long idPedido) {
        return find("SELECT i FROM ItemPedido i WHERE i.lote.id IN (SELECT l.id FROM Pedido p JOIN p.listaItemPedido l WHERE p.id = ?1)", idPedido).list();
    }
    

    


}
