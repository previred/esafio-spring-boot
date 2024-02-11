package com.previred.challenge.mapper;

import org.mapstruct.Mapper;

import com.previred.challenge.dto.EstadosTareaDto;
import com.previred.challenge.model.EstadosTarea;

@Mapper(componentModel = "spring")
public interface EstadosTareaMapper {
	EstadosTareaDto statusToDto(EstadosTarea task);
}
