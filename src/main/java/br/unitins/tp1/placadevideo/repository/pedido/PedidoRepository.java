package br.unitins.tp1.placadevideo.repository.pedido;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository  implements PanacheRepository<Pedido>{
    
    public List<Pedido> findByUsuario(Long idUsuario){
        return find("SELECT P FROM Pedido p WHERE p.usuario.id =?1", idUsuario).list();
    }

    public List<Pedido> findByUsername(String username){
        return find("SELECT P FROM Pedido p WHERE p.usuario.username =?1", username).list();
    }

}
