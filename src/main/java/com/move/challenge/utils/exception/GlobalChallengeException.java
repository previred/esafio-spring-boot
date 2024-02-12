package com.move.challenge.utils.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GlobalChallengeException extends RuntimeException {

   private ChallengeExceptionCode challengeExceptionCode;

   public GlobalChallengeException(ChallengeExceptionCode challengeExceptionCode) {
      this.challengeExceptionCode = challengeExceptionCode;
   }
   public GlobalChallengeException(String message, ChallengeExceptionCode challengeExceptionCode) {
      super(message);
      this.challengeExceptionCode = challengeExceptionCode;
   }

   @Override
   public String toString() {
      return challengeExceptionCode + " :: " + super.toString();
   }

}
