package cl.dpichinil.desafio.desafiospringboot.controller;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.service.TareaService;
import cl.dpichinil.desafio.desafiospringboot.service.UserService;
import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    AuthController authController;
    @Test
    void login() {
        UserDto dto = FillObject.fillUserDto();
        ResponseDto response = FillObject.fillResponseDto();
        response.setData("123asd");
        when(userService.login(dto)).thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));
        ResponseEntity<ResponseDto> responseEntity = authController.login(dto);

        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
        Assertions.assertEquals("123asd", responseEntity.getBody().getData());
    }

    @Test
    void encodePassword() {
        UserDto dto = FillObject.fillUserDto();
        ResponseDto response = FillObject.fillResponseDto();

        when(userService.login(dto)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = authController.login(dto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
    }

    @Test
    void logged() {
    }
}