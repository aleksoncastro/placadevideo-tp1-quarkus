package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.NotNull;

public record PaginacaoDTO(
    
@NotNull(message = "O número total de registros não pode ser nulo.")
Long totalRecords,

@NotNull(message = "O número da página não pode ser nulo.")
Integer page,

@NotNull(message = "O tamanho da página não pode ser nulo.")
Integer pageSize
) {
}
