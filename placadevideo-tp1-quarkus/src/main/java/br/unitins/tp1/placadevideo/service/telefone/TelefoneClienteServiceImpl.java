 package br.unitins.tp1.placadevideo.service.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.TelefoneCliente;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneClienteServiceImpl implements TelefoneClienteService {

    @Inject
    public TelefoneClienteRepository telefoneclienteRepository;

    @Inject
    public ClienteRepository  clienteRepository;


    @Override
    public TelefoneCliente findById(Long id) {
        return telefoneclienteRepository.findById(id);
    }

    @Override
    public List<TelefoneCliente> findByCliente(Long id) {
        return telefoneclienteRepository.findByTelefoneCliente(id);
    }

    @Override
    public List<TelefoneCliente> findAll() {
        return telefoneclienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneCliente create(TelefoneClienteRequestDTO dto){

        TelefoneCliente telefone = new TelefoneCliente();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneclienteRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public TelefoneCliente update(Long id, TelefoneClienteRequestDTO dto) {
        TelefoneCliente telefone = telefoneclienteRepository.findById(id);
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneclienteRepository.persist(telefone);

        return telefone;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneclienteRepository.deleteById(id);
    }

}
