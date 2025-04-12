package br.unitins.tp1.placadevideo.repository.funcionario;


import br.unitins.tp1.placadevideo.model.usuario.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    
    public PanacheQuery<Funcionario> findByNome(String nome) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT funcionario ");
        jpql.append("FROM Funcionario funcionario ");
        jpql.append("WHERE funcionario.nome LIKE ?1 ");
    
        return find(jpql.toString(), "%" + nome + "%");
    }
    
    public Funcionario findByCpf(String cpf) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT p ");
        jpql.append("FROM Funcionario p ");
        jpql.append("WHERE p.cpf = ?1 ");
    
        return find(jpql.toString(), cpf).firstResult();
    }

}
