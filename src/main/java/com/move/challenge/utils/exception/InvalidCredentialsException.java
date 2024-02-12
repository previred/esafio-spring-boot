package com.move.challenge.utils.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends GlobalChallengeException {

   public InvalidCredentialsException(ChallengeExceptionCode challengeExceptionCode) {
      super(challengeExceptionCode);
   }

   public InvalidCredentialsException(String message, ChallengeExceptionCode challengeExceptionCode) {
      super(message, challengeExceptionCode);
   }

}