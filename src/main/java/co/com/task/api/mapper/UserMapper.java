package co.com.task.api.mapper;

import co.com.task.api.domain.User;
import co.com.task.api.dto.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User updteUserDtoToUserDomain(User user, UserDTO userDto) {
	return User.builder().idUser(userDto.getIdUser()).name(userDto.getName())
		.email(userDto.getEmail().toLowerCase()).password(userDto.getPassword()).created(user.getCreated())
		.modified(user.getModified()).build();
    }

    public static User userDtoToUserDomain(UserDTO userDto) {
	return User.builder()
		.idUser(userDto.getIdUser())
		.name(userDto.getName())
		.email(userDto.getEmail().toLowerCase())
		.password(userDto.getPassword())
		.build();
    }

    public static UserDTO userDomainToUserDto(User user) {
	return UserDTO.builder()
		.idUser(user.getIdUser())
		.name(user.getName())
		.email(user.getEmail())
		.build();
    }

}
