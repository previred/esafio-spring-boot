package cl.dpichinil.desafio.desafiospringboot.service.impl;

import cl.dpichinil.desafio.desafiospringboot.config.exception.CustomException;
import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import cl.dpichinil.desafio.desafiospringboot.persistence.repository.UserRepository;
import cl.dpichinil.desafio.desafiospringboot.service.UserService;
import cl.dpichinil.desafio.desafiospringboot.util.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FunctionUtil functionUtil;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseDto> login(UserDto dto) {
        String module = Constant.MODULE_LOGIN;
        Optional<User> optional = userRepository.findByUsername(dto.getUsername());
        if(optional.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        }
        String hashText = SecurityFunction.getHashText(dto.getUsername(), dto.getPassword());
        if(!passwordEncoder.matches(hashText,optional.get().getPassword())){
            throw new CustomException(HttpStatus.UNAUTHORIZED, 1001, module);
        }
        User user = optional.get();
        user.setLastAccess(new Date());
        userRepository.save(user);
        String token = jwtService.generateToken(dto.getUsername());
        ResponseDto response = new ResponseDto(0);
        response = functionUtil.getMessage(response, module);
        response.setData(token);
        return functionUtil.generateResponseEntity(HttpStatus.CREATED, response);
    }

    @Override
    public ResponseEntity<ResponseDto> getByUsername(String username) {
        String module = Constant.MODULE_GET_USER_BY_USERNAME;
        if(validateUsername(username))
            throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, 1001, module);
        ResponseDto dto = new ResponseDto(0);
        dto = functionUtil.getMessage(dto, module);
        dto.setData(ParserUtil.parseUserToUserDto(optional.get()));
        return functionUtil.generateResponseEntity(HttpStatus.OK, dto);
    }

    @Override
    public ResponseEntity<ResponseDto> encodePassword(UserDto dto) {
        String module = Constant.MODULE_ENCODE_PASSWORD;
        String hashText = SecurityFunction.getHashText(dto.getUsername(), dto.getPassword());
        log.info(passwordEncoder.encode(hashText));
        ResponseDto response = new ResponseDto(0);
        response = functionUtil.getMessage(response, module);
        return functionUtil.generateResponseEntity(HttpStatus.OK, response);
    }

    @Override
    public ResponseEntity<ResponseDto> logged(HttpServletRequest request) {
        String module = Constant.MODULE_LOGGED;
        String token = SecurityFunction.getToken(request);
        String username = jwtService.extractUsername(token);
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isEmpty()){
            throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        }
        UserDto userDto = ParserUtil.parseUserToUserDto(optional.get());
        ResponseDto responseDto = new ResponseDto(0);
        responseDto = functionUtil.getMessage(responseDto, module);
        responseDto.setData(userDto);
        return functionUtil.generateResponseEntity(HttpStatus.OK, responseDto);
    }

    private boolean validateUsername(String username) {
        if(username != null) return false;
        if(username.length() == 0) return false;
        return true;
    }
}
