package cl.dpichinil.desafio.desafiospringboot.config.exception;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.util.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@org.springframework.web.bind.annotation.ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    private final FunctionUtil functionUtil;
    @ExceptionHandler({CustomException.class})
    public final ResponseEntity<ResponseDto> handleException(CustomException ex) {
        ResponseDto dto = new ResponseDto(ex.getCode());
        dto = functionUtil.getMessage(dto);
        return functionUtil.generateResponseEntity(ex.getStatus(), dto);
    }
}
