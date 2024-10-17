package br.unitins.tp1.placadevideo.repository.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.model.TelefoneCliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneClienteRepository implements PanacheRepository<TelefoneCliente> {
    
    public List<TelefoneCliente> findByTelefoneCliente(Long idTelefoneCliente ){
        return find("SELECT telefonecliente FROM TelefoneCliente telefonecliente Where telefonecliente.cliente.id = ?1", idTelefoneCliente ).list();
        
    }

}
