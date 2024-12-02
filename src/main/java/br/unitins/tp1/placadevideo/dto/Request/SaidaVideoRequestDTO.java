package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.SaidaVideo;

public record SaidaVideoRequestDTO(
    String tipo,
    Integer quantidade
) {
      public SaidaVideo intoEntity() {
        SaidaVideo saidaVideo = new SaidaVideo();
        saidaVideo.setTipo(tipo());
        saidaVideo.setQuantidade(quantidade());
        return saidaVideo;
    }
}
