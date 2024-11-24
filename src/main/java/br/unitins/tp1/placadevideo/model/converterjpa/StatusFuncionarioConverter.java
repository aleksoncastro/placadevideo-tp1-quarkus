package br.unitins.tp1.placadevideo.model.converterjpa;

import br.unitins.tp1.placadevideo.model.usuario.StatusFuncionario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusFuncionarioConverter implements AttributeConverter<StatusFuncionario, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusFuncionario statusfuncionario) {
      return statusfuncionario == null ? null : statusfuncionario.getId();
    }

    @Override
    public StatusFuncionario convertToEntityAttribute(Integer idStatusFuncionario) {
       return StatusFuncionario.valueOf(idStatusFuncionario);
    }
    
}