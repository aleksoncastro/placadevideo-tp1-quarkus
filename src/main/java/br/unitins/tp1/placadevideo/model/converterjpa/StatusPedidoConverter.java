package br.unitins.tp1.placadevideo.model.converterjpa;

import br.unitins.tp1.placadevideo.model.pedido.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusPedido statuspedido) {
      return statuspedido == null ? null : statuspedido.getId();
    }

    @Override
    public StatusPedido convertToEntityAttribute(Integer idStatusPedido) {
       return StatusPedido.valueOf(idStatusPedido);
    }
    
}