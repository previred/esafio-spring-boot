package com.spa.crud.mapper;

import com.spa.crud.dto.RolesDTO;
import com.spa.crud.dto.UsuariosDTO;
import com.spa.crud.model.Usuarios;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RolesDTO.class})
public interface UsuariosMapper {
    UsuariosDTO userToDTO(Usuarios usuarios);
}
