package com.move.challenge.utils.handler;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.move.challenge.dto.ErrorResponseDto;
import com.move.challenge.utils.exception.ChallengeExceptionCode;
import com.move.challenge.utils.exception.GlobalChallengeException;
import com.move.challenge.utils.exception.InvalidCredentialsException;

@RestControllerAdvice
public class RestControllerHandler {
   @ExceptionHandler(GlobalChallengeException.class)
   public ResponseEntity<ErrorResponseDto> handleChallengeException(GlobalChallengeException ex) {
      //TODO Aca se podria implementar un servicio de mensajeria.
      return createResponse(ex.getChallengeExceptionCode(), ex.getMessage());
   }

   @ExceptionHandler(InvalidCredentialsException.class)
   public ResponseEntity<ErrorResponseDto> handleChallengeException(InvalidCredentialsException ex) {
      //TODO Aca se podria implementar un servicio de mensajeria.
      return createResponse(ex.getChallengeExceptionCode(), ex.getMessage());
   }

   private ResponseEntity<ErrorResponseDto> createResponse(ChallengeExceptionCode processorGatewayExceptionCode, String message) {
      if (Objects.nonNull(message) && !message.isEmpty()) {
         return new ResponseEntity<>(toError(processorGatewayExceptionCode, message), processorGatewayExceptionCode.getHttpStatus());
      }
      return new ResponseEntity<>(toError(processorGatewayExceptionCode), processorGatewayExceptionCode.getHttpStatus());
   }

   private ErrorResponseDto toError(ChallengeExceptionCode challengeExceptionCode, String message) {
      return new ErrorResponseDto(challengeExceptionCode.getError(), message);
   }

   private ErrorResponseDto toError(ChallengeExceptionCode challengeExceptionCode) {
      return new ErrorResponseDto(challengeExceptionCode.getError(), challengeExceptionCode.getDescripcion());
   }


}
