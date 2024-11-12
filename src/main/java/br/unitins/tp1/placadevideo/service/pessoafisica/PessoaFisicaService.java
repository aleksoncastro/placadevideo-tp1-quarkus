package br.unitins.tp1.placadevideo.service.pessoafisica;

import br.unitins.tp1.placadevideo.dto.Request.PessoaFisicaRequestDTO;
import br.unitins.tp1.placadevideo.model.PessoaFisica;

import java.util.List;

public interface PessoaFisicaService {

    PessoaFisica findById(Long id);

    PessoaFisica findByCpf(String cpf);

    List<PessoaFisica> findByNome(String nome);

    List<PessoaFisica> findAll();

    PessoaFisica create(PessoaFisicaRequestDTO dto);

    PessoaFisica update(Long id, PessoaFisicaRequestDTO dto);

    void delete(Long id);


}
