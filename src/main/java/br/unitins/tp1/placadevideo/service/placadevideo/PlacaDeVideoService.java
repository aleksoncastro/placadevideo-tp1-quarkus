package br.unitins.tp1.placadevideo.service.placadevideo;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;

public interface PlacaDeVideoService {

    PlacaDeVideo findById(Long id);

    PlacaDeVideo findByDescricao(String  descricao);

    List<PlacaDeVideo> findByModelo(String modelo, Integer page, Integer pageSize);

    List<PlacaDeVideo> findAll(Integer page, Integer pageSize);

    PlacaDeVideo create(PlacaDeVideoRequestDTO dto);

    PlacaDeVideo update(Long id, PlacaDeVideoRequestDTO dto);

    PlacaDeVideo updateNomeImagem(Long id, String nomeImagem);

    void delete(Long id);

    long count();

    long count(String nome);


}
