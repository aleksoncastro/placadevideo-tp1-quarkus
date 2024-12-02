package br.unitins.tp1.placadevideo.dto.response;

import br.unitins.tp1.placadevideo.model.placadevideo.Memoria;

public record MemoriaResponseDTO(
    Long Id,
    String tipoMemoria,
    Integer capacidade,
    Integer larguraBanda,
    Integer velocidadeMemoria
) {
    public static  MemoriaResponseDTO valueOf(Memoria memoria) {
        return new MemoriaResponseDTO( 
        memoria.getId(),
        memoria.getTipoMemoria(),
        memoria.getCapacidade(),
        memoria.getLarguraBanda(),
        memoria.getVelocidadeMemoria()
        );
    } 

}
