package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.SaidaVideo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record SaidaVideoRequestDTO(
    @NotBlank(message = "O tipo da saída de vídeo deve ser informado.")
    String tipo,

    @NotNull(message = "A quantidade não pode ser nula.")
    @PositiveOrZero(message = "A quantidade deve ser zero ou um valor positivo.")
    Integer quantidade
) {
      public SaidaVideo intoEntity() {
        SaidaVideo saidaVideo = new SaidaVideo();
        saidaVideo.setTipo(tipo());
        saidaVideo.setQuantidade(quantidade());
        return saidaVideo;
    }
}
