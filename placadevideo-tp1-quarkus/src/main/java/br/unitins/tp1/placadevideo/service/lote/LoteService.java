package br.unitins.tp1.placadevideo.service.lote;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.LoteRequestDTO;
import br.unitins.tp1.placadevideo.model.Lote;

public interface LoteService {

    Lote findById(Long id);

    Lote findByCodigo(String codigo);

    List<Lote> findAll();

    Lote create(LoteRequestDTO dto);

    Lote update(Long id, LoteRequestDTO dto);

    void delete(Long id);


}
