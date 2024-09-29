package br.unitins.tp1.placadevideo.service;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.EstadoDTORequest;
import br.unitins.tp1.placadevideo.model.Estado;
import br.unitins.tp1.placadevideo.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    public Estado findById(Long id) {
        return estadoRepository.findById(id);
    }

    @Override
    public List<Estado> findByNome(String nome) {
        return estadoRepository.findByNome(nome);
    }

    @Override
    public List<Estado> findAll() {
        return estadoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Estado create(EstadoDTORequest dto) {
        Estado estado = new Estado();
        estado.setNome(dto.getNome());
        estado.setSigla(dto.getSigla());

        estadoRepository.persist(estado);
        return estado;
    }

    @Override
    @Transactional
    public Estado update(Long id, EstadoDTORequest dto) {
        Estado estado = estadoRepository.findById(id);
        estado.setNome(dto.getNome());
        estado.setSigla(dto.getSigla());

        return estado;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

}
