package cl.dpichinil.desafio.desafiospringboot.dto;

import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDtoTest {

    @Test
    void fillObject(){
        ResponseDto dto = FillObject.fillResponseDto();
        dto.setData(1);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(0, dto.getCode());
        Assertions.assertEquals("OK", dto.getMessage());
        Assertions.assertEquals(1, dto.getData());
    }
}