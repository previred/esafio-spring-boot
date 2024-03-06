package com.spa.crud.mapper;

import com.spa.crud.dto.RolesDTO;
import com.spa.crud.model.Roles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesDTO rolesToDto(Roles roles);
}
