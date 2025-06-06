package br.unitins.tp1.placadevideo.service.placadevideo;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.FiltroPlacaDeVideoDTO;
import br.unitins.tp1.placadevideo.dto.request.PaginacaoDTO;
import br.unitins.tp1.placadevideo.dto.request.PlacaDeVideoRequestDTO;
import br.unitins.tp1.placadevideo.dto.response.PlacaDeVideoResponseDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.PlacaDeVideo;

public interface PlacaDeVideoService {

    PlacaDeVideo findById(Long id);

    PlacaDeVideo findByDescricao(String descricao);

    List<PlacaDeVideo> findByModelo(String modelo, Integer page, Integer pageSize);

    PaginacaoDTO findAll(Integer page, Integer pageSize);

    List<PlacaDeVideoResponseDTO> findPage(int page, int pageSize);

    List<PlacaDeVideo> findByLancamentos(String prefixo1, String prefixo2, String valor1, String valor2);

    PlacaDeVideo create(PlacaDeVideoRequestDTO dto);

    PlacaDeVideo update(Long id, PlacaDeVideoRequestDTO dto);

    PlacaDeVideo updateNomeImagem(Long id, String nomeImagem);

    void delete(Long id);

    long count();

    long count(String nome);

    List<PlacaDeVideoResponseDTO> findByTexto(String texto, Integer page, Integer pageSize);

    List<PlacaDeVideoResponseDTO> findByFiltros(
            FiltroPlacaDeVideoDTO filtro,
            Integer page,
            Integer pageSize
    );

    List<PlacaDeVideoResponseDTO> findByTextoAndFiltros(
            String texto,
            FiltroPlacaDeVideoDTO filtro,
            Integer page,
            Integer pageSize
    );
}
