package com.previred.nspa.desafio.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.previred.nspa.entity.Usuarios;
import com.previred.nspa.model.UsuariosDTO;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {
    UsuariosMapper INSTANCE = Mappers.getMapper( UsuariosMapper.class );

    UsuariosDTO usuarioToUsuarioDTO(Usuarios usuario);
    Usuarios usuarioDTOToUsuario(UsuariosDTO usuarioDTO);

    List<UsuariosDTO> usuariosToUsuarioDTOs(List<Usuarios> usuarios);
    List<Usuarios> usuarioDTOsToUsuarios(List<UsuariosDTO> usuarioDTOs);
}
