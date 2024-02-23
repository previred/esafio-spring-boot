package cl.dpichinil.desafio.desafiospringboot.controller;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.TareaDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.service.UserService;
import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;
    @Test
    void getByUsername() {
        UserDto dto = FillObject.fillUserDto();
        ResponseDto response = FillObject.fillResponseDto();
        response.setData(dto);

        when(userService.getByUsername("dpichinil")).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = userController.getByUsername("dpichinil");

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
        Assertions.assertNotNull(responseEntity.getBody().getData());
    }
}