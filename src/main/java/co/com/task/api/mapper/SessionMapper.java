package co.com.task.api.mapper;

import co.com.task.api.domain.Session;
import co.com.task.api.dto.LoginResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionMapper {

    public static LoginResponseDTO sessionDomainToSessionDto(Session login) {
	return LoginResponseDTO.builder().token(login.getToken()).build();
    }

}
