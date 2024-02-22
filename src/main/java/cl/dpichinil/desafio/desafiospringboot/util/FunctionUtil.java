package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FunctionUtil {
    public <T> ResponseEntity<ResponseDto> generateResponseEntity(HttpStatus status, ResponseDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    public ResponseDto getMessage(ResponseDto dto) {
        if(dto.getCode() == 1000) dto.setMessage("message");
        return dto;
    }
}
