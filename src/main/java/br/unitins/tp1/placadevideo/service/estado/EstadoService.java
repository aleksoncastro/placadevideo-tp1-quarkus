package br.unitins.tp1.placadevideo.service.estado;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.model.Estado;
import jakarta.validation.Valid;

public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome, Integer page, Integer pageSize);

    List<Estado> findAll(Integer page, Integer pageSize);

    Estado create(@Valid EstadoRequestDTO dto);

    Estado update(Long id, EstadoRequestDTO dto);

    long count();

    long count(String nome);

    void delete(Long id);

}
