package com.previred.challenge.mapper;

import org.mapstruct.Mapper;

import com.previred.challenge.dto.EstadosTareaDto;
import com.previred.challenge.dto.TareasDto;
import com.previred.challenge.model.Tareas;

@Mapper(componentModel = "spring", uses = {EstadosTareaDto.class})
public interface TareasMapper {
	TareasDto taskToDto(Tareas task);
}