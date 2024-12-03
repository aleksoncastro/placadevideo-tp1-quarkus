package br.unitins.tp1.placadevideo.dto.request;

import jakarta.validation.constraints.Email;

public record EmailPatchRequestDTO(
    @Email
    String novoEmail
) {
    
}
