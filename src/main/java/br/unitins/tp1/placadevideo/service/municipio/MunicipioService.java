package br.unitins.tp1.placadevideo.service.municipio;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.MunicipioRequestDTO;
import br.unitins.tp1.placadevideo.model.Municipio;

public interface MunicipioService {

    Municipio findById(Long id);

    List<Municipio> findByNome(String nome, Integer page, Integer pageSize);

    List<Municipio> findAll(Integer page, Integer pageSize);

    Municipio create(MunicipioRequestDTO dto);

    Municipio update(Long id, MunicipioRequestDTO dto);

    void delete(Long id);

    long count();

    long count(String nome);

}