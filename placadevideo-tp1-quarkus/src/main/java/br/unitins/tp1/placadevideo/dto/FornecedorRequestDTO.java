package br.unitins.tp1.placadevideo.dto;

import java.util.List;


import jakarta.validation.constraints.NotBlank;

public record FornecedorRequestDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotBlank(message = "O campo CNPJ deve ser informado.")
    String cnpj,
    @NotBlank(message = "O campo email deve ser informado.")
    String email,
    List<TelefoneRequestDTO> telefones,

    List<EnderecoRequestDTO> enderecos
) {

}
