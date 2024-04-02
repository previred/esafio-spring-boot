package com.previred.nspa.desafio.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.previred.nspa.entity.Tareas;
import com.previred.nspa.model.TareasDTO;

@Mapper(componentModel = "spring")
public interface TareasMapper {

    TareasMapper INSTANCE = Mappers.getMapper( TareasMapper.class ); 


    TareasDTO tareasToTareasDTO(Tareas tareas);
    Tareas tareasDTOToTareas(TareasDTO tareasDTO);

    List<TareasDTO> tareasToTareasDTOs(List<Tareas> tareas);
    List<Tareas> tareasDTOsToTareas(List<TareasDTO> tareasDTOs);
}
