package cl.dpichinil.desafio.desafiospringboot.service;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;

public interface UserService {
    ResponseEntity<ResponseDto> login(UserDto dto);

    ResponseEntity<ResponseDto> getByUsername(String username);
}
