package br.unitins.tp1.placadevideo.repository.cartao;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao> {
    
    public List<Cartao> findByTitular(String titular ){
        return find("SELECT cartao FROM Cartao cartao Where cartao.titular LIKE ?1", "%"+ titular + "%").list();
    }

}
