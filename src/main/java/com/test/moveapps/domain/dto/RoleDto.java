package com.test.moveapps.domain.dto;

import com.test.moveapps.domain.entity.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoleDto {

    @NotNull(message = "{value.not.null}")
    @NotEmpty(message = "{value.not.empty}")
    private String name;

    public RoleDto() {
//         Empty
    }

    public RoleDto(String name) {
        this.name = name;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Role convert(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }

    public static RoleDto convert(Role role) {
        return new RoleDto(role.getName());
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
