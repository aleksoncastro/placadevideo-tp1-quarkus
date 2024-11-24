package br.unitins.tp1.placadevideo.repository.pagamento;

import br.unitins.tp1.placadevideo.model.pagamento.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {
    
    
}