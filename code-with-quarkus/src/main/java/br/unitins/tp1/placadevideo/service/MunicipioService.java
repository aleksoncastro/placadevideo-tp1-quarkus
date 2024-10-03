package br.unitins.tp1.placadevideo.service;

import br.unitins.tp1.placadevideo.dto.MunicipioRequestDTO;
import br.unitins.tp1.placadevideo.model.Municipio;

import java.util.List;

public interface MunicipioService {

    Municipio findById(Long id);

    List<Municipio> findByNome(String nome);

    List<Municipio> findAll();

    Municipio create(MunicipioRequestDTO dto);

    Municipio update(Long id, MunicipioRequestDTO dto);

    void delete(Long id);


}