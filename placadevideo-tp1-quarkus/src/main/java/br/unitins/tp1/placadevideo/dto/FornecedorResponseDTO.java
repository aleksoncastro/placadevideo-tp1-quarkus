package br.unitins.tp1.placadevideo.dto;

import  java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    String  cnpj,
    String email, 
    List<TelefoneResponseDTO> telefones
) {

    public static  FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(), 
            fornecedor.getCnpj(),
            fornecedor.getEmail(),
            fornecedor.getTelefones().stream()
            .map(TelefoneResponseDTO::valueOf)
            .collect(Collectors.toList())
        );
    }
    
}
