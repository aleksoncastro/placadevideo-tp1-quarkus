package br.unitins.tp1.placadevideo.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.Endereco;
import br.unitins.tp1.placadevideo.model.Fornecedor;
import br.unitins.tp1.placadevideo.model.Telefone;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    String cnpj,
    String email
    ///List<Telefone> telefones
    //List<Endereco> enderecos

) {
    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor){
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getCnpj(),
            fornecedor.getEmail()
  
        );
        //    // fornecedor.getTelefones().stream()
        //     .map(TelefoneResponseDTO::valueOf).collect(Collectors.toList()),
        //     fornecedor.getEnderecos().stream()
        //     .map(EnderecoResponseDTO::valueOf)
        //     .collect(Collectors.toList())
    }
}

