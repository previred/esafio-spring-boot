package com.move.challenge.utils.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.move.challenge.dto.ErrorResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeExceptionCode {

   INTERNAL_ERROR("generic_error", HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error generico inesperado"),
   UNAUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED, "Error de autenticación: Credenciales incorrectas"),
   NOT_FOUND("not_found", HttpStatus.NOT_FOUND, "No se ha encontrado el elemento solicitado");
   private final String error;
   private final HttpStatus httpStatus;
   private final String descripcion;

}
