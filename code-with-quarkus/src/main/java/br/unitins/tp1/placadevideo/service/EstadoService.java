package br.unitins.tp1.placadevideo.service;

import br.unitins.tp1.placadevideo.dto.EstadoDTORequest;
import br.unitins.tp1.placadevideo.model.Estado;

import java.util.List;

public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome);

    List<Estado> findAll();

    Estado create(EstadoDTORequest dto);

    Estado update(Long id, EstadoDTORequest dto);

    void delete(Long id);


}
