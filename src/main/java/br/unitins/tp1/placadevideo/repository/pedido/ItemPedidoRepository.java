package br.unitins.tp1.placadevideo.repository.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.model.pedido.ItemPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {

    public List<ItemPedido> findByPedidoId(Long pedidoId) {
        String jpql = "SELECT i FROM ItemPedido i WHERE i.pedido.id = ?1";
        return find(jpql, pedidoId).list();
    }
    
    

    


}
