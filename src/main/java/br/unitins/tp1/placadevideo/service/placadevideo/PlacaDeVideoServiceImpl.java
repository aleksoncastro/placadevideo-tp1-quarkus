package br.unitins.tp1.placadevideo.service.placadevideo;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.Request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Fan;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.placadevideo.PlacaDeVideoRepository;

import br.unitins.tp1.placadevideo.service.lote.LoteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlacaDeVideoServiceImpl implements PlacaDeVideoService {

    @Inject
    public PlacaDeVideoRepository placaDeVideoRepository;

    @Inject
    public LoteService loteService;

   /* @Inject
    public FornecedorService fornecedorService;
 */
    @Override
    public PlacaDeVideo findById(Long id) {
        return placaDeVideoRepository.findById(id);
    }

    @Override
    public PlacaDeVideo findByDescricao(String  descricao) {
        return placaDeVideoRepository.findByDescricao(descricao);
    }

    @Override
    public List<PlacaDeVideo> findByModelo(String modelo) {
        return placaDeVideoRepository.findByModelo(modelo);
    }

    @Override
    public List<PlacaDeVideo> findAll() {
        return placaDeVideoRepository.findAll().list();
    }

    @Override
    @Transactional
    public PlacaDeVideo create(PlacaDeVideoRequestDTO dto) {
        PlacaDeVideo placaDeVideo = new PlacaDeVideo();
        
        placaDeVideo.setModelo(dto.modelo());
        placaDeVideo.setCategoria(dto.categoria());
        placaDeVideo.setPreco(dto.preco());
        placaDeVideo.setVram(dto.vram());
        placaDeVideo.setResolucao(dto.resolucao());
        placaDeVideo.setEnergia(dto.energia());
        placaDeVideo.setDescricao(dto.descricao());
        placaDeVideo.setCompatibilidade(dto.compatibilidade());
        placaDeVideo.setClokBase(dto.clockBase());
        placaDeVideo.setClockBoost(dto.clockBoost());
        placaDeVideo.setFan(Fan.valueOf(dto.idFan()));
       // placaDeVideo.setFornecedor(fornecedorService.findById(dto.idFornecedor()));
        //Atualiza o placadevideo no banco
        placaDeVideoRepository.persist(placaDeVideo);

        return placaDeVideo;
    }

    @Override
    @Transactional
    public PlacaDeVideo update(Long id, PlacaDeVideoRequestDTO dto) {
        PlacaDeVideo placaDeVideo = placaDeVideoRepository.findById(id);
        if (placaDeVideo == null) {
            throw new EntityNotFoundException("PlacaDeVideo não encontrado");
        }

        placaDeVideo.setModelo(dto.modelo());
        placaDeVideo.setCategoria(dto.categoria());
        placaDeVideo.setPreco(dto.preco());
        placaDeVideo.setVram(dto.vram());
        placaDeVideo.setResolucao(dto.resolucao());
        placaDeVideo.setEnergia(dto.energia());
        placaDeVideo.setDescricao(dto.descricao());
        placaDeVideo.setCompatibilidade(dto.compatibilidade());
        placaDeVideo.setClokBase(dto.clockBase());
        placaDeVideo.setClockBoost(dto.clockBoost());
        placaDeVideo.setFan(Fan.valueOf(dto.idFan()));
       // placaDeVideo.setFornecedor(fornecedorService.findById(dto.idFornecedor()));

        // Persistindo as alterações do placadevideo
        placaDeVideoRepository.persist(placaDeVideo);
        return placaDeVideo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        placaDeVideoRepository.deleteById(id);
    }

}
