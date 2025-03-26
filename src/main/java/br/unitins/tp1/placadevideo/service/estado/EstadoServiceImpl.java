package br.unitins.tp1.placadevideo.service.estado;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EstadoRequestDTO;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.repository.estado.EstadoRepository;
import br.unitins.tp1.placadevideo.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    public Estado findById(Long id) {
        return estadoRepository.findById(id);
    }

    @Override
    public List<Estado> findByNome(String nome, Integer page, Integer pageSize) {
        return estadoRepository.findByNome(nome).page(page, pageSize).list();
    }

    public List<Estado> findByNome(String nome) {
        return estadoRepository.findByNome(nome).list();
    }

    @Override
    public List<Estado> findAll(Integer page, Integer pageSize) {
        PanacheQuery<Estado> query = null;
        if (page == null || pageSize == null)
            query = estadoRepository.findAll();
        else
            query = estadoRepository.findAll().page(page, pageSize);

        return query.list();
    }

    @Override
    public long count() {
        return estadoRepository.findAll().count();
    }

    @Override
    public long count(String nome) {
        return estadoRepository.findByNome(nome).count();
    }

    @Override
    @Transactional
    public Estado create(@Valid EstadoRequestDTO dto) {
        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        validarSigla(dto.sigla());

        estadoRepository.persist(estado);
        return estado;
    }

    private void validarSigla(String sigla) {
        Estado estado = estadoRepository.findBySigla(sigla);
        if (estado != null)
            throw new ValidationException("sigla", "Esta sigla já foi utilizada por outro estado.");
    }

    @Override
    @Transactional
    public Estado update(Long id, EstadoRequestDTO dto) {
        Estado estado = estadoRepository.findById(id);
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        return estado;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

}
