package br.unitins.tp1.placadevideo.service;

import br.unitins.tp1.placadevideo.dto.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.model.Estado;

import java.util.List;

public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome);

    List<Estado> findAll();

    Estado create(EstadoRequestDTO dto);

    Estado update(Long id, EstadoRequestDTO dto);

    void delete(Long id);


}
