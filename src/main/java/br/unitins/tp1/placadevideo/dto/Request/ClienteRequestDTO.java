package br.unitins.tp1.placadevideo.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "O campo deve ser informado") 
        String nome,
        @NotBlank(message = "O campo deve ser informado") 
        LocalDate dataNascimento,
        @Valid
        List<EnderecoRequestDTO> enderecos,
        @Valid
        List<TelefoneClienteRequestDTO> telefones) {

}
