package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import org.hibernate.Hibernate;

import br.unitins.tp1.placadevideo.dto.request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.lote.LoteRepository;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;
import br.unitins.tp1.placadevideo.service.placadevideo.PlacaDeVideoService;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    public List<Lote> findByCodigo(String codigo, Integer page, Integer pageSize) {
        return loteRepository.findByCodigo(codigo).page(page, pageSize).list();
    }

    @Override
    public List<Lote> findAll(Integer page, Integer pageSize) {
        PanacheQuery<Lote> query = null;
        if (page == null || pageSize == null)
            query = loteRepository.findAll();
        else 
            query = loteRepository.findAll().page(page, pageSize);

        return query.list();
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
    public Lote create(@Valid LoteRequestDTO dto) {
        // Validações de campos
        if (dto.codigo() == null || dto.codigo().isEmpty()) {
            throw new ValidationException("codigo", "O código do lote é obrigatório.");
        }
        if (dto.estoque() <= 0) {
            throw new ValidationException("estoque", "O estoque do lote deve ser maior que zero.");
        }
        if (dto.dataFabricacao() == null) {
            throw new ValidationException("dataFabricacao", "A data de fabricação é obrigatória.");
        }

        Lote lote = new Lote();
        lote.setCodigo(dto.codigo());
        lote.setEstoque(dto.estoque());
        lote.setDataFabricacao(dto.dataFabricacao());

        PlacaDeVideo placaDeVideo = placaDeVideoService.findById(dto.idPlacaDeVideo());
        if (placaDeVideo == null) {
            throw new EntityNotFoundException("Placa de vídeo não encontrada");
        }
        Hibernate.initialize(placaDeVideo.getListaImagem());
        
        lote.setPlacaDeVideo(placaDeVideo);

        // Atualiza o lote no banco
        loteRepository.persist(lote);

        return lote;
    }

    @Override
    @Transactional
    public Lote update(Long id, @Valid LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id);
        if (lote == null) {
            throw new EntityNotFoundException("Lote não encontrado");
        }

        // Validações de campos
        if (dto.codigo() == null || dto.codigo().isEmpty()) {
            throw new ValidationException("codigo", "O código do lote é obrigatório.");
        }
        if (dto.estoque() <= 0) {
            throw new ValidationException("estoque", "O estoque do lote deve ser maior que zero.");
        }
        if (dto.dataFabricacao() == null) {
            throw new ValidationException("dataFabricacao", "A data de fabricação é obrigatória.");
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

    @Override
    public long count() {
        return loteRepository.findAll().count();
    }

    @Override
    public long count(String codigo) {
        return loteRepository.findByCodigo(codigo).count();
    }

}
