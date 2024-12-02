 package br.unitins.tp1.placadevideo.service.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.TelefoneClienteRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneCliente;
import br.unitins.tp1.placadevideo.repository.cliente.ClienteRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneClienteServiceImpl implements TelefoneClienteService {
    
    @Inject
    public TelefoneClienteRepository telefoneClienteRepository;
    
    @Inject
    public ClienteRepository  clienteRepository;
    
    
    @Override
    public TelefoneCliente findById(Long id) {
        return telefoneClienteRepository.findById(id);
    }
    
    @Override
    public TelefoneCliente findByNumero(String numero) {
        return telefoneClienteRepository.findByNumero(numero);
    }

    @Override
    public TelefoneCliente findByCodigo(String codigo){
        return telefoneClienteRepository.findByNumero(codigo);
    }
    

    @Override
    public List<TelefoneCliente> findByCliente(Long id) {
        return telefoneClienteRepository.findByTelefoneCliente(id);
    }

    @Override
    public List<TelefoneCliente> findAll() {
        return telefoneClienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneCliente create(TelefoneClienteRequestDTO dto){

        TelefoneCliente telefone = new TelefoneCliente();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneClienteRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public TelefoneCliente update(Long id, TelefoneClienteRequestDTO dto) {
        TelefoneCliente telefone = telefoneClienteRepository.findById(id);
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneClienteRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneClienteRepository.deleteById(id);
    }





}
