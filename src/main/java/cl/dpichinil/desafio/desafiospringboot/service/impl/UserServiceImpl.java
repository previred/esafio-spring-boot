package cl.dpichinil.desafio.desafiospringboot.service.impl;

import cl.dpichinil.desafio.desafiospringboot.config.exception.CustomException;
import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import cl.dpichinil.desafio.desafiospringboot.persistence.repository.UserRepository;
import cl.dpichinil.desafio.desafiospringboot.service.UserService;
import cl.dpichinil.desafio.desafiospringboot.util.FunctionUtil;
import cl.dpichinil.desafio.desafiospringboot.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FunctionUtil functionUtil;

    @Override
    public ResponseEntity<ResponseDto> login(UserDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> getByUsername(String username) {
        if(validateUsername(username))
            throw new CustomException(HttpStatus.BAD_REQUEST, 1000);
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, 1001);
        ResponseDto dto = new ResponseDto(0);
        dto.setData(ParserUtil.parseUserToUserDto(optional.get()));
        return functionUtil.generateResponseEntity(HttpStatus.OK, dto);
    }

    private boolean validateUsername(String username) {
        if(username != null) return false;
        if(username.length() == 0) return false;
        return true;
    }
}
