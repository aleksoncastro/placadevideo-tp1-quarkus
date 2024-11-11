package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.Lote;
import br.unitins.tp1.placadevideo.model.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.lote.LoteRepository;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoteServiceImpl implements LoteService {
    
    @Inject
    public LoteRepository loteRepository;

    @Inject
    public PlacaDeVideoRepository placaDeVideoRepository;
    
    @Override
    public Lote findById(Long id) {
        return loteRepository.findById(id);
    }
    
    @Override
    public Lote findByCodigo(String codigo) {
        return loteRepository.findByCodigo(codigo);
    }

    @Override
    public List<Lote> findAll() {
        return loteRepository.findAll().list();
    }
    
    @Override
    public Lote findByIdPlacaDeVideo(Long idPlaca) {
        return loteRepository.findByIdPlacaDeVideo(idPlaca);
    }
    
    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        Lote lote = new Lote();
        lote.setCodigo(dto.codigo());
        lote.setQuantidade(dto.quantidade());
        lote.setDataFabricacao(dto.dataFabricacao());
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(dto.idPlacaDeVideo());
        if (placaDeVideo == null) {
            throw new RuntimeException("Placa de vídeo não encontrada com o ID fornecido.");
        }
        lote.setPlacaDeVideo(placaDeVideo);

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
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(dto.idPlacaDeVideo());
        if (placaDeVideo == null) {
            throw new RuntimeException("Placa de vídeo não encontrada com o ID fornecido.");
        }
        lote.setPlacaDeVideo(placaDeVideo);


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
