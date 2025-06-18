package br.unitins.tp1.placadevideo.repository.cliente;

import java.util.List;

import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    
    public List<Cliente> findByNome(String nome) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT cliente ");
        jpql.append("FROM Cliente cliente ");
        jpql.append("WHERE cliente.nome LIKE ?1 ");
    
        return find(jpql.toString(), "%" + nome + "%").list();
    }
    
    public Cliente findByUsername(String username) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT c ");
        jpql.append("FROM Cliente c ");
        jpql.append("WHERE c.usuario.username = ?1 ");
    
        return find(jpql.toString(), username).firstResult();
    }

    public Cliente findByIdUsuario(Long idUsuario) {
    StringBuffer jpql = new StringBuffer();
    jpql.append("SELECT c ");
    jpql.append("FROM Cliente c ");
    jpql.append("WHERE c.usuario.id = ?1 ");

    return find(jpql.toString(), idUsuario).firstResult();
}


    
    


}
