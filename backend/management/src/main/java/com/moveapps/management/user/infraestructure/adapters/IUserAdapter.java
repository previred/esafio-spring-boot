package com.moveapps.management.user.infraestructure.adapters;

import com.moveapps.management.user.domains.data.UserDTO;
import com.moveapps.management.user.infraestructure.entities.UserEntity;

public interface IUserAdapter {
	UserEntity toEntity(UserDTO userDTO);
}