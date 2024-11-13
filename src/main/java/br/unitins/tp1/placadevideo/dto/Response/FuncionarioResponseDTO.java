package br.unitins.tp1.placadevideo.dto.Response;

import java.time.LocalDate;
import  java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.usuario.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    String nome,
    String  cpf,
    LocalDate dataNascimento,
    String email, 
    Double salario,
    List<TelefoneFuncionarioResponseDTO> telefones
) {

    public static  FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
            funcionario.getId(),
            funcionario.getNome(), 
            funcionario.getCpf(),
            funcionario.getDataNascimento(),
            funcionario.getEmail(),
            funcionario.getSalario(),
            funcionario.getTelefones().stream()
            .map(TelefoneFuncionarioResponseDTO::valueOf)
            .collect(Collectors.toList())

        );
    }
    
}