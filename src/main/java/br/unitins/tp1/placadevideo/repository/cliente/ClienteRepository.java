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
    
    public Cliente findByCpf(String cpf) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT p ");
        jpql.append("FROM Cliente p ");
        jpql.append("WHERE p.cpf = ?1 ");
    
        return find(jpql.toString(), cpf).firstResult();
    }

}
