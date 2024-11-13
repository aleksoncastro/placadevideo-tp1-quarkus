package br.unitins.tp1.placadevideo.repository.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneFuncionarioRepository implements PanacheRepository<TelefoneFuncionario> {
    
    public List<TelefoneFuncionario> findByTelefoneFuncionario(Long idTelefoneFuncionario ){
        return find("SELECT telefonefuncionario FROM TelefoneFuncionario telefonefuncionario Where telefonefuncionario.funcionario.id = ?1", idTelefoneFuncionario ).list();
        
    }

    public TelefoneFuncionario findByNumero(String numero) {
        return find("SELECT t FROM TelefoneFuncionario t WHERE t.numero = ?1", numero ).firstResult();
    }

    public TelefoneFuncionario findByCodigo(String codigo) {
        return find("SELECT t FROM TelefoneFuncionario t WHERE p.codigoarea = ?1", codigo ).firstResult();
    }

}
