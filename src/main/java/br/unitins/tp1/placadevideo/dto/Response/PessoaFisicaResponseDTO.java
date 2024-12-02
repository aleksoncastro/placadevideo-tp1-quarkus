package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.Sexo;
import br.unitins.tp1.placadevideo.model.usuario.PessoaFisica;

public record PessoaFisicaResponseDTO(Long id, String nome, String cpf, Sexo sexo, String nomeImagem) {

    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoafisica){
        return new PessoaFisicaResponseDTO(
            pessoafisica.getId(), 
            pessoafisica.getNome(), 
            pessoafisica.getCpf(), 
            pessoafisica.getSexo(),
            pessoafisica.getNomeImagem());
    }

}
