 package br.unitins.tp1.placadevideo.service.telefone;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.Request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.model.telefone.TelefoneFuncionario;
import br.unitins.tp1.placadevideo.repository.funcionario.FuncionarioRepository;
import br.unitins.tp1.placadevideo.repository.telefone.TelefoneFuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneFuncionarioServiceImpl implements TelefoneFuncionarioService {
    
    @Inject
    public TelefoneFuncionarioRepository telefoneFuncionarioRepository;
    
    @Inject
    public FuncionarioRepository  funcionarioRepository;
    
    
    @Override
    public TelefoneFuncionario findById(Long id) {
        return telefoneFuncionarioRepository.findById(id);
    }
    
    @Override
    public TelefoneFuncionario findByNumero(String numero) {
        return telefoneFuncionarioRepository.findByNumero(numero);
    }

    @Override
    public TelefoneFuncionario findByCodigo(String codigo){
        return telefoneFuncionarioRepository.findByNumero(codigo);
    }
    

    @Override
    public List<TelefoneFuncionario> findByFuncionario(Long id) {
        return telefoneFuncionarioRepository.findByTelefoneFuncionario(id);
    }

    @Override
    public List<TelefoneFuncionario> findAll() {
        return telefoneFuncionarioRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneFuncionario create(TelefoneFuncionarioRequestDTO dto){

        TelefoneFuncionario telefone = new TelefoneFuncionario();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneFuncionarioRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public TelefoneFuncionario update(Long id, TelefoneFuncionarioRequestDTO dto) {
        TelefoneFuncionario telefone = telefoneFuncionarioRepository.findById(id);
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefoneFuncionarioRepository.persist(telefone);
        return telefone;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneFuncionarioRepository.deleteById(id);
    }





}
