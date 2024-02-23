package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.config.properties.MessageProperties;
import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FunctionUtilTest {
    @Autowired
    MessageProperties messageProperties;
    FunctionUtil functionUtil;

    @BeforeEach
    void setUp() {
        this.functionUtil = new FunctionUtil(messageProperties);
    }

    @Test
    void generateResponseEntity() {
        ResponseDto response = FillObject.fillResponseDto();
        response.setData("data");
        ResponseEntity<ResponseDto> responseEntity = functionUtil.generateResponseEntity(HttpStatus.OK, response);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(0, responseEntity.getBody().getCode());
        Assertions.assertEquals("OK", responseEntity.getBody().getMessage());
        Assertions.assertEquals("data", responseEntity.getBody().getData());
    }

    @Test
    void getMessage() {
        ResponseDto response = FillObject.fillResponseDto();
        response = functionUtil.getMessage(response, Constant.MODULE_GET_USER_BY_USERNAME);
        Assertions.assertNotNull(response.getMessage());
    }
}