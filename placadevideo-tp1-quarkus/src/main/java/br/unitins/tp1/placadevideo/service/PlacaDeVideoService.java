package br.unitins.tp1.placadevideo.service;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.model.PlacaDeVideo;

public interface PlacaDeVideoService {

    PlacaDeVideo findById(Long id);

    List<PlacaDeVideo> findByModelo(String modelo);

    List<PlacaDeVideo> findAll();

    PlacaDeVideo create(PlacaDeVideoRequestDTO dto);

    PlacaDeVideo update(Long id, PlacaDeVideoRequestDTO dto);

    void delete(Long id);


}
