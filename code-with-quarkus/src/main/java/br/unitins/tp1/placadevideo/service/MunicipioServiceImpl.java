package br.unitins.tp1.placadevideo.service;

import java.util.List;

import br.unitins.tp1.placadevideo.model.Municipio;
import br.unitins.tp1.placadevideo.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService{

    @Inject
    public MunicipioRepository municipioRepository;

    @Inject
    public EstadoService estadoService;

    @Override
    public Municipio findById(Long id) {
       return municipioRepository.findById(id);
    }

    @Override
    public List<Municipio> findByNome(String nome) {
        return municipioRepository.findByNome(nome);
    }

    @Override
    public List<Municipio> findAll() {
        return municipioRepository.findAll().list();
    }

    @Override
    @Transactional
    public Municipio create(Municipio municipio) {
       
        municipio.setEstado(estadoService.findById(municipio.getEstado().getId()));
        
        municipioRepository.persist(municipio);
       return municipio;       
    }

    @Override
    @Transactional
    public Municipio update(Municipio municipio) {
        Municipio m = municipioRepository.findById(municipio.getEstado().getId());
        m.setNome(municipio.getNome());
        //buscando o estado apartir de um id do municipio
        m.setEstado(estadoService.findById(municipio.getId()));
       
        return m;
    }

    @Override
    @Transactional
    public void delete(Long id) {
      municipioRepository.deleteById(id);
    }

}
