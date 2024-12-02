package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.lote.LoteRepository;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
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

    @Inject
    public PlacaDeVideoService placaDeVideoService;

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
    public List<Lote> findByIdPlacaDeVideoQtdeTotal(Long idPlacaDeVideo) {
        return loteRepository.findByIdPlacaDeVideoQtdeTotal(idPlacaDeVideo);
    }


    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        Lote lote = new Lote();
        lote.setCodigo(dto.codigo());
        lote.setEstoque(dto.estoque());
        lote.setDataFabricacao(dto.dataFabricacao());
        PlacaDeVideo placaDeVideo = placaDeVideoService.findById(dto.idPlacaDeVideo());
        if (placaDeVideo == null) {
            throw new EntityNotFoundException("Placa de vídeo não encontrada");
        }
        lote.setPlacaDeVideo(placaDeVideo);

        // Atualiza o lote no banco
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
        lote.setEstoque(dto.estoque());
        lote.setDataFabricacao(dto.dataFabricacao());
        PlacaDeVideo placaDeVideo = placaDeVideoService.findById(dto.idPlacaDeVideo());
        if (placaDeVideo == null) {
            throw new EntityNotFoundException("Placa de vídeo não encontrada");
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
