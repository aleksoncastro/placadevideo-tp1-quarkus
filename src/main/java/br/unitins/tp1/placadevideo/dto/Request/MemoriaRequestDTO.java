package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.Memoria;

public record MemoriaRequestDTO(
    String tipoMemoria,
    Integer capacidade,
    Integer larguraBanda,
    Integer velocidadeMemoria
) {
    public Memoria intoEntity() {
        Memoria memoria = new Memoria();
        memoria.setTipoMemoria(tipoMemoria());
        memoria.setCapacidade(capacidade());
        memoria.setLarguraBanda(larguraBanda());
        memoria.setVelocidadeMemoria(velocidadeMemoria());
        return memoria;
    }
}
