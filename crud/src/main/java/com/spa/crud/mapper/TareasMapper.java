package com.spa.crud.mapper;

import com.spa.crud.dto.EstadosTareaDTO;
import com.spa.crud.dto.TareasDTO;
import com.spa.crud.model.Tareas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EstadosTareaDTO.class})
public interface TareasMapper {
    TareasDTO taskToDto(Tareas tareas);
}
