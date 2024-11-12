package br.unitins.tp1.placadevideo.repository.cliente;

import java.util.List;

import br.unitins.tp1.placadevideo.model.usuario.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    
    @Inject
    EntityManager em;

    public List<Cliente> findByNome(String nome ){
        return find("SELECT cliente FROM Cliente cliente Where cliente.nome LIKE ?1", "%"+ nome + "%").list();
    }

    public void deleteClienteEndereco(Long clienteId, Long enderecoId) {
        em.createQuery("DELETE FROM Endereco e WHERE e.cliente.id = :clienteId AND e.id = :enderecoId")
          .setParameter("clienteId", clienteId)
          .setParameter("enderecoId", enderecoId)
          .executeUpdate();
    }

    public Cliente findByCpf(String cpf) {
        return find("SELECT p FROM Cliente p WHERE p.cpf = ?1",  cpf ).firstResult();
    }

}
