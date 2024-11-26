package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.Request.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.placadevideo.Lote;

public interface LoteService {

    Lote findById(Long id);

    Lote findByCodigo(String codigo);

    Lote findByIdPlacaDeVideo(Long idPlaca );

    List<Lote> findAll();

    List<Lote> findByIdPlacaDeVideoQtdeTotal(Long idPlacaDeVideo);

    Lote create(LoteRequestDTO dto);

    Lote update(Long id, LoteRequestDTO dto);

    void delete(Long id);


}
