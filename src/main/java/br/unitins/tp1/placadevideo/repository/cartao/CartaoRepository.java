package br.unitins.tp1.placadevideo.repository.cartao;

import java.util.List;

import br.unitins.tp1.placadevideo.model.usuario.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao> {
    
    public List<Cartao> findByTitular(String titular ){
        return find("SELECT cartao FROM Cartao cartao Where cartao.titular LIKE ?1", "%"+ titular + "%").list();
    }

    public Cartao findFirstCartaoByClienteId(Long clienteId) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT cartao ");
        jpql.append("FROM Cliente cliente ");
        jpql.append("JOIN cliente.cartoes cartao ");
        jpql.append("WHERE cliente.id = ?1 ");
    
        return find(jpql.toString(), clienteId).firstResult();
    }

    public Cartao findFirstCartaoByUsername(String username) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT cartao ");
        jpql.append("FROM Cliente cliente ");
        jpql.append("JOIN cliente.usuario usuario ");
        jpql.append("JOIN cliente.cartoes cartao ");
        jpql.append("WHERE usuario.username = ?1 ");
    
        return find(jpql.toString(), username).firstResult();
    }
    

}