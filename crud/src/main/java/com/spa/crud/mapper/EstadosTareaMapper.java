package com.spa.crud.mapper;

import com.spa.crud.dto.EstadosTareaDTO;
import com.spa.crud.model.EstadosTarea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadosTareaMapper {
    EstadosTareaDTO statusToDto(EstadosTarea estadosTarea);
}
