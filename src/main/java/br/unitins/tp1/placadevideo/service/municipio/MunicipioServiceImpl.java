package br.unitins.tp1.placadevideo.service.municipio;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.MunicipioRequestDTO;
import br.unitins.tp1.placadevideo.model.Municipio;
import br.unitins.tp1.placadevideo.repository.municipio.MunicipioRepository;
import br.unitins.tp1.placadevideo.service.estado.EstadoService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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
    public List<Municipio> findByNome(String nome, Integer page, Integer pageSize) {
        return municipioRepository.findByNome(nome).page(page, pageSize).list();
    }

    @Override
    public List<Municipio> findAll(Integer page, Integer pageSize) {
         PanacheQuery<Municipio> query = null;
         if (page == null || pageSize == null)
             query = municipioRepository.findAll();
         else 
             query = municipioRepository.findAll().page(page, pageSize);
 
         return query.list();
     }

     public List<Municipio> findByNome(String nome) {
        return municipioRepository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Municipio create(MunicipioRequestDTO dto) {
       Municipio municipio = new Municipio();
       municipio.setNome(dto.nome());
       municipio.setEstado(estadoService.findById(dto.idEstado()));
       municipioRepository.persist(municipio);
       
       return municipio;       
    }

    @Override
    @Transactional
    public Municipio update(Long id, MunicipioRequestDTO dto) {
        Municipio municipio = municipioRepository.findById(id); 
        municipio.setNome(dto.nome());
        municipio.setEstado(estadoService.findById(dto.idEstado()));
        //buscando o estado apartir de um id do municipio
       
        return municipio;
    }

    @Override
    @Transactional
    public void delete(Long id) {
      municipioRepository.deleteById(id);
    }

    @Override
     public long count() {
         return municipioRepository.findAll().count();
     }
 
     @Override
     public long count(String nome) {
         return municipioRepository.findByNome(nome).count();
     }

}