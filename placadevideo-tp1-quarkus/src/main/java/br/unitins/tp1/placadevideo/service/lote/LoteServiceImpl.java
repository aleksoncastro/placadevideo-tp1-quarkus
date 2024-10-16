package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.Lote;
import br.unitins.tp1.placadevideo.repository.lote.LoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoteServiceImpl implements LoteService {

    @Inject
    public LoteRepository loteRepository;

    @Override
    public Lote findById(Long id) {
        return loteRepository.findById(id);
    }

    @Override
    public List<Lote> findAll() {
        return loteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        Lote lote = new Lote();
        lote.setCodigo(dto.codigo());
        lote.setQuantidade(dto.quantidade());
        lote.setDataFabricacao(dto.dataFabricacao());

        //Atualiza o lote no banco
        loteRepository.persist(lote);

        return lote;
    }


  
    @Override
    @Transactional
    public Lote update(Long id, LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id);
        if (lote == null) {
            throw new EntityNotFoundException("Lote não encontrado");
        }
        lote.setCodigo(dto.codigo());
        lote.setQuantidade(dto.quantidade());
        lote.setDataFabricacao(dto.dataFabricacao());

        // Persistindo as alterações do lote
        loteRepository.persist(lote);
        return lote;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loteRepository.deleteById(id);
    }

}
