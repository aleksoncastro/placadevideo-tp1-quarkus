package br.unitins.tp1.placadevideo.repository.usuario;

import br.unitins.tp1.placadevideo.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    
    public Usuario findByUsernameAndSenha(String username, String senha ){
        return find("SELECT usuario FROM Usuario usuario Where usuario.username LIKE ?1 AND usuario.senha =?2", username, senha).firstResult();
    }

    public Usuario findByUsername(String username ){
        return find("SELECT usuario FROM Usuario usuario Where usuario.username LIKE ?1", username).firstResult();
    }

    public Usuario findByEmail (String email) {
        return find("SELECT u FROM Usuario u WHERE u.email =?1", email).firstResult();
    }

    public Usuario findByCpf(String cpf) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("SELECT p ");
        jpql.append("FROM Usuario p ");
        jpql.append("WHERE p.cpf = ?1 ");
    
        return find(jpql.toString(), cpf).firstResult();
    }

}
