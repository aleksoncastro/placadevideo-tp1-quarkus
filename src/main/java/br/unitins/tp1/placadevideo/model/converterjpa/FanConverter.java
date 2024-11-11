package br.unitins.tp1.placadevideo.model.converterjpa;

import br.unitins.tp1.placadevideo.model.Fan;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FanConverter implements AttributeConverter<Fan, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Fan fan) {
      return fan == null ? null : fan.getId();
    }

    @Override
    public Fan convertToEntityAttribute(Integer idFan) {
       return Fan.valueOf(idFan);
    }
    
}