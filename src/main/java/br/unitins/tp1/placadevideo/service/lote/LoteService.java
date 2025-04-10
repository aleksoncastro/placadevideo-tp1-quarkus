package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;

public interface LoteService {

    Lote findById(Long id);

    List<Lote> findByCodigo(String codigo, Integer page, Integer pageSize);

    Lote findByIdPlacaDeVideo(Long idPlaca );

    List<Lote> findAll(Integer page, Integer pageSize);
     
    long count();
     
    long count(String nome);

    List<Lote> findByIdPlacaDeVideoQtdeTotal(Long idPlacaDeVideo);

    Lote create(LoteRequestDTO dto);

    Lote update(Long id, LoteRequestDTO dto);

    void delete(Long id);


}
