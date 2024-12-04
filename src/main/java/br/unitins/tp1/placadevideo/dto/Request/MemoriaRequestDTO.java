package br.unitins.tp1.placadevideo.dto.request;

import br.unitins.tp1.placadevideo.model.placadevideo.Memoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemoriaRequestDTO(
    @NotBlank(message = "Tipo de memória não pode ser em branco") String tipoMemoria,
    @NotNull(message = "Capacidade não pode ser nula") Integer capacidade,
    @NotNull(message = "Largura de banda não pode ser nula") Integer larguraBanda,
    @NotNull(message = "Velocidade da memória não pode ser nula") Integer velocidadeMemoria
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
