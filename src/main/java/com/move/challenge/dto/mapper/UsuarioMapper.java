package com.move.challenge.dto.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.move.challenge.dto.UsuarioDto;
import com.move.challenge.dto.UsuarioMinDto;
import com.move.challenge.entity.UsuarioEntity;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
@Named("usuarioMapper")
public interface UsuarioMapper {

   UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

   @Named("toUsuarioMinDto")
   UsuarioMinDto toUsuarioMinDto(UsuarioEntity entity);

   @Named("toUsuarioDto")
   UsuarioDto toUsuarioDto(UsuarioEntity entity);

   @Named("toUsuarioDtoList")
   @IterableMapping(qualifiedByName="toUsuarioDto")
   List<UsuarioDto> toUsuarioDto(List<UsuarioEntity> entity);

}
