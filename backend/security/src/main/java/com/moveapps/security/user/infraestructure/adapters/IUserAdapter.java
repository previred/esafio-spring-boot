package com.moveapps.security.user.infraestructure.adapters;

import com.moveapps.security.user.domains.data.UserDTO;
import com.moveapps.security.user.infraestructure.entities.UserEntity;

public interface IUserAdapter {
	UserEntity toEntity(UserDTO userDTO);
}