package br.unitins.tp1.placadevideo.repository.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneClienteRepository implements PanacheRepository<TelefoneCliente> {
    
    public List<TelefoneCliente> findByTelefoneCliente(Long idTelefoneCliente ){
        return find("SELECT telefonecliente FROM TelefoneCliente telefonecliente Where telefonecliente.cliente.id = ?1", idTelefoneCliente ).list();
        
    }

    public TelefoneCliente findByNumero(String numero) {
        return find("SELECT t FROM TelefoneCliente t WHERE t.numero = ?1", numero ).firstResult();
    }

    public TelefoneCliente findByCodigo(String codigo) {
        return find("SELECT t FROM TelefoneCliente t WHERE p.codigoarea = ?1", codigo ).firstResult();
    }

}
