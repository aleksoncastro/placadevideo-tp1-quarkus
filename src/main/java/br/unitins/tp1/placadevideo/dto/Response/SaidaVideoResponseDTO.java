package br.unitins.tp1.placadevideo.dto.Response;

import br.unitins.tp1.placadevideo.model.placadevideo.SaidaVideo;

public record SaidaVideoResponseDTO(
    Long Id,
    String tipo,
    Integer quantidade
) {
    public static  SaidaVideoResponseDTO valueOf(SaidaVideo saidaVideo) {
        return new SaidaVideoResponseDTO(
          saidaVideo.getId(),
          saidaVideo.getTipo(),
          saidaVideo.getQuantidade()
        );
    } 

}
