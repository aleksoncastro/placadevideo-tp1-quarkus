package br.unitins.tp1.placadevideo.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "O campo deve ser informado") String nome,
        @NotBlank(message = "O campo deve ser informado") String cpf,
        @NotBlank(message = "O campo deve ser informado") LocalDate dataNascimento,
        List<EnderecoRequestDTO> enderecos,
        List<TelefoneClienteRequestDTO> telefones) {

}
