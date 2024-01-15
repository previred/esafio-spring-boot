package com.nuevoapp.prueba.domain.model.dto;

import com.nuevoapp.prueba.domain.model.enums.UserRolesEnum;
import com.nuevoapp.prueba.domain.model.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class UserDto {
    private String email;
    private UserRolesEnum role;
    private String name;
    private String lastName;
    private UserStatusEnum status;
    private ZonedDateTime lastLoginDate;
}
