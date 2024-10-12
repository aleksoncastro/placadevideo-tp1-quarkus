package br.unitins.tp1.placadevideo.service;

import br.unitins.tp1.placadevideo.model.PlacaDeVideo;

import java.util.List;

public interface PlacaDeVideoService {

    PlacaDeVideo findById(Long id);

    List<PlacaDeVideo> findByModelo(String modelo);

    List<PlacaDeVideo> findAll();

    PlacaDeVideo create(PlacaDeVideo placaDeVideo);

    PlacaDeVideo update(PlacaDeVideo placaDeVideo);

    void delete(Long id);
}
