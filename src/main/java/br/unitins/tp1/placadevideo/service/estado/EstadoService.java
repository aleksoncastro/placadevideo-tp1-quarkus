package br.unitins.tp1.placadevideo.service.estado;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.model.Estado;
import jakarta.validation.Valid;

public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome);

    List<Estado> findAll();

    Estado create(@Valid EstadoRequestDTO dto);

    Estado update(Long id, EstadoRequestDTO dto);

    void delete(Long id);

}
