package br.unitins.tp1.placadevideo.repository.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneFornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneFornecedorRepository implements PanacheRepository<TelefoneFornecedor> {
    
    public List<TelefoneFornecedor> findByTelefoneFornecedor(Long idTelefoneFornecedor ){
        return find("SELECT telefonefornecedor FROM TelefoneFornecedor telefonefornecedor Where telefonefornecedor.fornecedor.id = ?1", idTelefoneFornecedor ).list();
        
    }

    public TelefoneFornecedor findByNumero(String numero) {
        return find("SELECT t FROM TelefoneFornecedor t WHERE t.numero = ?1", numero ).firstResult();
    }

}
