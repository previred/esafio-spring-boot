package com.moveapps.security.user.infraestructure.adapters;
import static com.moveapps.security.user.infraestructure.confing.Constants.*;

import org.springframework.stereotype.Component;

import com.moveapps.commons.api.infraestructure.adapters.EndPointGenericAdapterImpl;
import com.moveapps.security.user.domains.data.UserDTO;
import com.moveapps.security.user.infraestructure.entities.UserEntity;



@Component
public class UserAdapter extends EndPointGenericAdapterImpl<UserDTO, UserEntity> implements IUserAdapter{

	@Override
	public String nameModule() {
		return MANAGEMENT_MODULE;
	}

	@Override
	public String className() {
		return USER_ADAPTER;
	}

	@Override
	public UserDTO toModelDTO(UserEntity entity) {
		return new UserDTO().builder()
							.id(entity.getId())
							.firstName(entity.getFirstName())
							.lastName(entity.getLastName())
							.phoneNumber(entity.getPhoneNumber())
							.username(entity.getUsername())
							.password(entity.getPassword())
							.build();
	}

	@Override
	public UserEntity toEntity(UserDTO userDTO) {
		return  new UserEntity().builder()
							.id(userDTO.getId())
							.firstName(userDTO.getFirstName())
							.lastName(userDTO.getLastName())
							.phoneNumber(userDTO.getPhoneNumber())
							.username(userDTO.getUsername())
							.password(userDTO.getPassword())
							.build();
	}

}