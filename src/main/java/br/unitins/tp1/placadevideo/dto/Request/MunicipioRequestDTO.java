package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record MunicipioRequestDTO(
@NotBlank(message = "O campo nome deve ser informado")    
String nome,
@NotNull(message = "O idEstado não pode ser nulo") 
Long idEstado ) {

}
