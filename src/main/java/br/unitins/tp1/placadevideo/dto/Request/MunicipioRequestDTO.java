package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record MunicipioRequestDTO(
@NotBlank(message = "O campo nome deve ser informado")   
@Size(min = 2, max = 60, message = "A o nome deve possuir no mínimo 2 caracteres") 
String nome,
@NotNull(message = "O idEstado não pode ser nulo") 
Long idEstado ) {

}
