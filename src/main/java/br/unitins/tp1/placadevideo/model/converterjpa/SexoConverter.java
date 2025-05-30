package br.unitins.tp1.placadevideo.model.converterjpa;

import br.unitins.tp1.placadevideo.model.Sexo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Sexo sexo) {
      return sexo == null ? null : sexo.getId();
    }

    @Override
    public Sexo convertToEntityAttribute(Integer idSexo) {
       return Sexo.valueOf(idSexo);
    }
    
}
