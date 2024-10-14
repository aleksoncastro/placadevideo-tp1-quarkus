package br.unitins.tp1.placadevideo.repository;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByCliente(Long idCliente ){
        return find("SELECT endereco FROM Endereco endereco Where endereco.cliente.id = ?1", "%"+ idCliente + "%" ).list();
        
    }

}
