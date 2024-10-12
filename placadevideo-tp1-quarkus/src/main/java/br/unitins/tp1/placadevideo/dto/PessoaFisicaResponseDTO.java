package br.unitins.tp1.placadevideo.dto;

import br.unitins.tp1.placadevideo.model.PessoaFisica;
import br.unitins.tp1.placadevideo.model.Sexo;

public record PessoaFisicaResponseDTO(Long id, String nome, String cpf, Sexo sexo) {

    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoafisica){
        return new PessoaFisicaResponseDTO(
            pessoafisica.getId(), 
            pessoafisica.getNome(), 
            pessoafisica.getCpf(), 
            pessoafisica.getSexo());
    }

}
