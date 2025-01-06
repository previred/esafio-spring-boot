package cl.previred.taskmanagement.infrastructure.exception;

import cl.previred.taskmanagement.application.dto.response.EstadoRespuestaDTO;
import cl.previred.taskmanagement.application.dto.response.RespuestaDTO;
import cl.previred.taskmanagement.application.dto.response.RespuestaTokenDTO;
import cl.previred.taskmanagement.core.domain.constant.CodigosEnum;
import cl.previred.taskmanagement.core.domain.exception.*;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<RespuestaDTO> handleUsuarioNotFoundException(UsuarioNoEncontradoException ex) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.USUARIO_NO_ENCONTRADO.getCode(), CodigosEnum.USUARIO_NO_ENCONTRADO.getMessage()));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TareaNoEncontradaException.class)
    public ResponseEntity<RespuestaDTO> handleUsuarioNotFoundException(TareaNoEncontradaException ex) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.NO_SE_ENCUENTRAN_TAREAS.getCode(), CodigosEnum.NO_SE_ENCUENTRAN_TAREAS.getMessage()));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TableroNoEncontradoException.class)
    public ResponseEntity<RespuestaDTO> handleTableroNoEncontradoException(TableroNoEncontradoException ex) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.NO_SE_ENCUENTRA_TABLERO.getCode(), CodigosEnum.NO_SE_ENCUENTRA_TABLERO.getMessage()));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TableroDuplicadoException.class)
    public ResponseEntity<RespuestaDTO> handleTableroDuplicadoException(TableroDuplicadoException ex) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.TABLERO_DUPLICADO.getCode(), CodigosEnum.TABLERO_DUPLICADO.getMessage()));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        String mensaje = errorMap.toString();

        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.REQUEST_INVALIDO.getCode(), mensaje));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<RespuestaDTO> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.REQUEST_INVALIDO.getCode(), CodigosEnum.REQUEST_INVALIDO.getMessage()));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<RespuestaDTO> handleCustomException(CustomException ex) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.ERROR_DEL_SERVIDOR.getCode(), ex.getMessage()));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespuestaDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {


        ValueInstantiationException valueInstantiationException = (ValueInstantiationException) ex.getCause();
        IllegalArgumentException illegalArgumentException = (IllegalArgumentException) valueInstantiationException.getCause();
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.REQUEST_INVALIDO.getCode(), "Valor: "+ illegalArgumentException + " no es permitido"));
        return new ResponseEntity<>(respuestaDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CredencialesIncorrectasException.class)
    public ResponseEntity<RespuestaTokenDTO> handleCredencialesIncorrectasException(CredencialesIncorrectasException ex) {
        RespuestaTokenDTO respuestaDTO = new RespuestaTokenDTO();
        respuestaDTO.setCodigo(CodigosEnum.CREDENCIALES_INCORRECTAS.getCode());
        respuestaDTO.setDescripcion(CodigosEnum.CREDENCIALES_INCORRECTAS.getMessage());
        return new ResponseEntity<>(respuestaDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<RespuestaTokenDTO> handleTokenException(TokenException ex) {
        RespuestaTokenDTO respuestaDTO = new RespuestaTokenDTO();
        respuestaDTO.setCodigo(CodigosEnum.CREDENCIALES_INCORRECTAS.getCode());
        respuestaDTO.setDescripcion(CodigosEnum.CREDENCIALES_INCORRECTAS.getMessage());
        return new ResponseEntity<>(respuestaDTO, HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaTokenDTO> handleException(Exception ex) {
        RespuestaTokenDTO respuestaDTO = new RespuestaTokenDTO();
        respuestaDTO.setCodigo(CodigosEnum.ERROR_DEL_SERVIDOR.getCode());
        respuestaDTO.setDescripcion(ex.getMessage());
        return new ResponseEntity<>(respuestaDTO, HttpStatus.UNAUTHORIZED);
    }





}
