package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.SaidaVideo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaidaVideoRequestDTO(
    @NotBlank(message = "Tipo não pode ser em branco") String tipo,
    @NotNull(message = "Quantidade não pode ser nula") Integer quantidade
) {
      public SaidaVideo intoEntity() {
        SaidaVideo saidaVideo = new SaidaVideo();
        saidaVideo.setTipo(tipo());
        saidaVideo.setQuantidade(quantidade());
        return saidaVideo;
    }
}
