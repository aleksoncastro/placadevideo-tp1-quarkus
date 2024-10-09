package br.unitins.tp1.placadevideo.dto;

import java.time.LocalDate;

import br.unitins.tp1.placadevideo.model.Endereco;

public record ClienteRequestDTO(
    String nome,
    LocalDate dataNascimento,
    String email, 
    Endereco endereco
) {
    
}
