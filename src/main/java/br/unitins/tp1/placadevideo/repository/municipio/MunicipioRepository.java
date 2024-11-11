package br.unitins.tp1.placadevideo.repository.municipio;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.model.Municipio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {
    
    public List<Municipio> findByNome(String nome ){
        //return find("SELECT m FROM Municipio m Where m.nome LIKE ?1", "%"+ nome + "%").list();
        return find("nome LIKE ?1", "%"+ nome + "%").list();
    }

    public List<Municipio> findByNome(Estado estado ){
        return find("SELECT municipio FROM Municipio municipio Where municipio.estado.id LIKE ?1", estado.getId() ).list();
        
    }
}


