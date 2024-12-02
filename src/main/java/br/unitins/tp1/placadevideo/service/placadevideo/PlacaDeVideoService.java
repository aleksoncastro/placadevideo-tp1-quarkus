package br.unitins.tp1.placadevideo.service.placadevideo;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;

public interface PlacaDeVideoService {

    PlacaDeVideo findById(Long id);

    PlacaDeVideo findByDescricao(String  descricao);

    List<PlacaDeVideo> findByModelo(String modelo);

    List<PlacaDeVideo> findAll();

    PlacaDeVideo create(PlacaDeVideoRequestDTO dto);

    PlacaDeVideo update(Long id, PlacaDeVideoRequestDTO dto);

    void delete(Long id);


}
