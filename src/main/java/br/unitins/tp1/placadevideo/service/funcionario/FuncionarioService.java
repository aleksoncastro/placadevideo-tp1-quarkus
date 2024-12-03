package br.unitins.tp1.placadevideo.service.funcionario;

import java.util.List;

import br.unitins.tp1.placadevideo.dto.request.FuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.dto.request.TelefoneFuncionarioRequestDTO;
import br.unitins.tp1.placadevideo.model.usuario.Funcionario;

public interface FuncionarioService {

    Funcionario findById(Long id);

    List<Funcionario> findByNome(String nome);

    Funcionario findByCpf(String cpf);

    List<Funcionario> findAll();

    Funcionario create(String Username, FuncionarioRequestDTO dto) ;

    void addTelefone(Long funcionarioId, TelefoneFuncionarioRequestDTO dto);

    Funcionario update(Long idFuncionario, Long telefoneId, FuncionarioRequestDTO dto);

    void delete(Long id);


}
