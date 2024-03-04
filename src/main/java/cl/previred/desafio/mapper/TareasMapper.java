package cl.previred.desafio.mapper;

import org.mapstruct.Mapper;

import cl.previred.desafio.dto.EstadosTareaDto;
import cl.previred.desafio.dto.TareasDto;
import cl.previred.desafio.model.Tareas;

@Mapper(componentModel = "spring", uses = {EstadosTareaDto.class})
public interface TareasMapper {
    TareasDto taskToDto(Tareas task);
}