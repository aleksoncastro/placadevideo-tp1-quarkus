package br.unitins.tp1.placadevideo.repository.fornecedor;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {
    
    public List<Fornecedor> findByNome(String nome ){
        return find("SELECT fornecedor FROM Fornecedor fornecedor Where fornecedor.nome LIKE ?1", "%"+ nome + "%").list();
    }

    public Fornecedor findByCnpj(String cnpj) {
        return find("SELECT f FROM Fornecedor f WHERE f.cnpj = ?1",  cnpj ).firstResult();
    }

    public Fornecedor findByIdComTelefones(Long id) {
        return find("SELECT f FROM Fornecedor f LEFT JOIN FETCH f.telefones WHERE f.id = ?1", id).firstResult();
    }


}
