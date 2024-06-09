package com.test.moveapps.service;

import com.test.moveapps.domain.dto.RoleDto;
import com.test.moveapps.domain.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> store(RoleDto roleDto);

    Optional<Role> search(String roleName);

    List<Role> search();

    void destroy(String roleName);
}
