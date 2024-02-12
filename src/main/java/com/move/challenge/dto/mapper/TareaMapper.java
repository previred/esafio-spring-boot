package com.move.challenge.dto.mapper;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.format.annotation.DateTimeFormat;

import com.move.challenge.dto.TareaCreateDto;
import com.move.challenge.dto.TareaDto;
import com.move.challenge.entity.TareaEntity;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {EstadoTareaMapper.class, UsuarioMapper.class})
@Named("tareaMapper")
public interface TareaMapper {

   TareaMapper INSTANCE = Mappers.getMapper(TareaMapper.class);

   @Named("toTareaDto")
   @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd HH:mm:ss")
   @Mapping(target = "usuario", qualifiedByName = { "usuarioMapper", "toUsuarioMinDto" })
   @Mapping(target = "estado", qualifiedByName = { "estadoTareaMapper", "toEstadoTareaDto" })
   TareaDto toTareaDto(TareaEntity entities);

   @Named("toTareaDtoList")
   @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd HH:mm:ss")
   @IterableMapping(qualifiedByName="toTareaDto")
   List<TareaDto> toTareaDto(List<TareaEntity> entities);

   @Mapping(target = "usuario.id", source = "usuario")
   @Mapping(target = "estado.id", source = "estado")
   @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd HH:mm:ss")
   TareaEntity toTareaEntity(TareaCreateDto dto);

}
