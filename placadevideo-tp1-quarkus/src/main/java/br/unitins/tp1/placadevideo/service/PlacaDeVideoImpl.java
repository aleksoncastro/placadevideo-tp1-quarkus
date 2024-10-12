package br.unitins.tp1.placadevideo.service;

import java.util.List;

import br.unitins.tp1.placadevideo.model.PlacaDeVideo;
import br.unitins.tp1.placadevideo.repository.PlacaDeVideoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlacaDeVideoImpl implements PlacaDeVideoService {

    @Inject
    public PlacaDeVideoRepository placaDeVideoRepository;

    @Override
    public PlacaDeVideo findById(Long id) {
      return placaDeVideoRepository.findById(id);
    }

    @Override
    public List<PlacaDeVideo> findByModelo(String modelo) {
       return placaDeVideoRepository.findbyMoedelo(modelo);
    }

    @Override
    public List<PlacaDeVideo> findAll() {
       return placaDeVideoRepository.findAll().list();
    }

    @Override
    @Transactional
    public PlacaDeVideo create(PlacaDeVideo placaDeVideo) {
      placaDeVideoRepository.persist(placaDeVideo);
      return placaDeVideo;
        
    }

    @Override
public PlacaDeVideo update(PlacaDeVideo placaDeVideo) {
    PlacaDeVideo pcv = placaDeVideoRepository.findById(placaDeVideo.getId());
    pcv.setModelo(placaDeVideo.getModelo());
    pcv.setCategoria(placaDeVideo.getCategoria());
    pcv.setPreco(placaDeVideo.getPreco());
    pcv.setVram(placaDeVideo.getVram());
    pcv.setResolucao(placaDeVideo.getResolucao());
    pcv.setEnergia(placaDeVideo.getEnergia());
    pcv.setDescricao(placaDeVideo.getDescricao());
    pcv.setCompatibilidade(placaDeVideo.getCompatibilidade());
    return pcv;
}

    @Override
    @Transactional
    public void delete(Long id) {
       placaDeVideoRepository.deleteById(id);
    }

   
   


}
