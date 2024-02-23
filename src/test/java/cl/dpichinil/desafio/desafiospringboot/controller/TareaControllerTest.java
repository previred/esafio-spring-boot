package cl.dpichinil.desafio.desafiospringboot.controller;

import cl.dpichinil.desafio.desafiospringboot.dto.EstadoTareaDto;
import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.TareaDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.EstadoTarea;
import cl.dpichinil.desafio.desafiospringboot.service.TareaService;
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
class TareaControllerTest {
    @Mock
    TareaService tareaService;
    @InjectMocks
    TareaController tareaController;
    @Test
    void getAllEstadoTareaActive() {
        EstadoTareaDto dto = EstadoTareaDto.builder()
                .id(1)
                .descripcion("description")
                .activo(true)
                .build();
        List<EstadoTareaDto> list = new ArrayList<>();
        list.add(dto);
        ResponseDto response = ResponseDto.builder()
                .code(0)
                .message("OK")
                .data(list)
                .build();
        when(tareaService.getAllEstadoTarea()).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = tareaController.getAllEstadoTareaActive();
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
        Assertions.assertNotNull(responseEntity.getBody().getData());
    }

    @Test
    void getAllTareasActivas() {
        List<TareaDto> list = FillObject.fillListTareaDto();
        ResponseDto response = FillObject.fillResponseDto();
        response.setData(list);

        when(tareaService.getAll()).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = tareaController.getAllTareasActivas();

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
        Assertions.assertNotNull(responseEntity.getBody().getData());
    }

    @Test
    void create() {
        TareaDto dto = FillObject.fillTareaDto();
        ResponseDto response = FillObject.fillResponseDto();
        response.setData(dto.getId());

        when(tareaService.create(dto)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = tareaController.create(dto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
        Assertions.assertNotNull(responseEntity.getBody().getData());
    }

    @Test
    void update() {
        TareaDto dto = FillObject.fillTareaDto();
        ResponseDto response = FillObject.fillResponseDto();

        when(tareaService.update(dto)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = tareaController.update(dto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
    }

    @Test
    void delete() {
        ResponseDto response = FillObject.fillResponseDto();

        when(tareaService.delete(1)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        ResponseEntity<ResponseDto> responseEntity = tareaController.delete(1);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
    }
}