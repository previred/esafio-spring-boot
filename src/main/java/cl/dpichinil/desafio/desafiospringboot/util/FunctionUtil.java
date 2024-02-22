package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.config.properties.MessageProperties;
import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FunctionUtil {
    private final MessageProperties messageProperties;
    public <T> ResponseEntity<ResponseDto> generateResponseEntity(HttpStatus status, ResponseDto dto) {
        return new ResponseEntity<>(dto, status);
    }

    public ResponseDto getMessage(ResponseDto dto, String module) {
        switch (module){
            case Constant.MODULE_GET_USER_BY_USERNAME -> dto.setMessage(messageProperties.getGetUserByUsername().get(dto.getCode()));
            case Constant.MODULE_ENCODE_PASSWORD -> dto.setMessage(messageProperties.getEncodePassword().get(dto.getCode()));
            case Constant.MODULE_LOGIN -> dto.setMessage(messageProperties.getLogin().get(dto.getCode()));
            case Constant.MODULE_LOGGED -> dto.setMessage(messageProperties.getLogin().get(dto.getCode()));
        }
        return dto;
    }
}
