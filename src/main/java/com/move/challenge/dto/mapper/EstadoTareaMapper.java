package com.move.challenge.dto.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.move.challenge.dto.EstadoTareaDto;
import com.move.challenge.entity.EstadoTareaEntity;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
@Named("estadoTareaMapper")
public interface EstadoTareaMapper {

   EstadoTareaMapper INSTANCE = Mappers.getMapper(EstadoTareaMapper.class);

   @Named("toEstadoTareaDto")
   EstadoTareaDto toEstadoTareaDto(EstadoTareaEntity entity);

   @Named("toEstadoTareaDtoList")
   @IterableMapping(qualifiedByName="toEstadoTareaDto")
   List<EstadoTareaDto> toEstadoTareaDto(List<EstadoTareaEntity> entity);

}
