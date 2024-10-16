package br.unitins.tp1.placadevideo.repository.placadevideo;

import java.util.List;

import br.unitins.tp1.placadevideo.model.PlacaDeVideo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlacaDeVideoRepository implements PanacheRepository<PlacaDeVideo>{
    
    public List<PlacaDeVideo> findbyModelo(String modelo){
        return find("SELECT p FROM PlacaDeVideo p WHERE p.modelo LIKE ?1", "%" + modelo + "%").list();
    }

}
