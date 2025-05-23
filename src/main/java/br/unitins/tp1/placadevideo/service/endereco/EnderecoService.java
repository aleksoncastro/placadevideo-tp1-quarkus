package br.unitins.tp1.placadevideo.service.endereco;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Endereco;

public interface EnderecoService {

    Endereco findById(Long id);

    Endereco findByCep(String cep);

    List<Endereco> findByCliente(Long idCliente);

    List<Endereco> findAll();

    Endereco create(EnderecoRequestDTO dto);


    Endereco update(Long id, EnderecoRequestDTO dto);

    void delete(Long id);

    void deleteByCliente(Long idCliente);


}
