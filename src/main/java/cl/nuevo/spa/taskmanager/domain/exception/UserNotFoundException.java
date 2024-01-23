package cl.nuevo.spa.taskmanager.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

  private static final String NO_FOUND_USER_EXCEPTION = "No user was found for the provided id: %s";
  private static final String NO_FOUND_USER_GENERIC_EXCEPTION = "No user was found";

  public UserNotFoundException(String userId, String errorCode) {
    super(
        String.format(NO_FOUND_USER_EXCEPTION, userId),
        new Exception(),
        errorCode,
        HttpStatus.NOT_FOUND.value());
  }

  public UserNotFoundException(String errorCode) {
    super(
        NO_FOUND_USER_GENERIC_EXCEPTION, new Exception(), errorCode, HttpStatus.NOT_FOUND.value());
  }
}
