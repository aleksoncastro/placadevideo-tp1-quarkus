package br.unitins.tp1.placadevideo.service;

import br.unitins.tp1.placadevideo.dto.EnderecoRequestDTO;
import br.unitins.tp1.placadevideo.model.Cliente;
import br.unitins.tp1.placadevideo.model.Endereco;

import java.util.List;

public interface EnderecoService {

    Endereco findById(Long id);

    List<Endereco> findByCliente(Long idCliente);

    List<Endereco> findAll();

    Endereco create(EnderecoRequestDTO dto, Cliente cliente);

    Endereco update(Long id, EnderecoRequestDTO dto);

    void delete(Long id);


}
