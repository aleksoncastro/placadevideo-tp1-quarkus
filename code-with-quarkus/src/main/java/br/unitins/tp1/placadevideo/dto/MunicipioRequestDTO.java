package br.unitins.tp1.placadevideo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record MunicipioRequestDTO(
@NotBlank(message = "O campo nome deve ser informado")    
String nome,
@NotNull(message = "O idEstadi não pode ser nulo") 
Long idEstado ) {

}
