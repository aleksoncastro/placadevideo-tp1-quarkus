package br.unitins.tp1.placadevideo.repository.endereco;

import java.util.List;

import br.unitins.tp1.placadevideo.model.pedido.EnderecoEntrega;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoEntregaRepository implements PanacheRepository<EnderecoEntrega> {

    public List<EnderecoEntrega> findByCliente(Long idCliente ){
        return find("SELECT enderecoentrega FROM EnderecoEntrega enderecoentrega Where enderecoentrega.cliente.id = ?1", idCliente ).list();
        
    }

    public void deleteClienteEnderecoEntrega(Long idCliente) {
        delete("DELETE FROM cliente_enderecoentrega WHERE cliente_id = ?1", idCliente);
    }

    public EnderecoEntrega findByCep(String cep) {
        return find("SELECT e FROM EnderecoEntrega e WHERE e.cep = ?1", cep ).firstResult();
    }

    public void deleteByCliente(long idCliente){
        delete("DELETE FROM EnderecoEntrega WHERE cliente.id = ?1", idCliente);

    }
}
