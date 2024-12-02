package br.unitins.tp1.placadevideo.dto.response;

import  java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.placadevideo.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    String  cnpj,
    String email, 
    List<TelefoneFornecedorResponseDTO> telefones
) {

    public static  FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(), 
            fornecedor.getCnpj(),
            fornecedor.getEmail(),
            fornecedor.getTelefones().stream()
            .map(TelefoneFornecedorResponseDTO::valueOf)
            .collect(Collectors.toList())
        );
    }
    
}
