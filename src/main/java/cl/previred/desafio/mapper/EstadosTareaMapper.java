package cl.previred.desafio.mapper;

import org.mapstruct.Mapper;

import cl.previred.desafio.dto.EstadosTareaDto;
import cl.previred.desafio.model.EstadosTarea;

@Mapper(componentModel = "spring")
public interface EstadosTareaMapper {
    EstadosTareaDto statusToDto(EstadosTarea task);
}
