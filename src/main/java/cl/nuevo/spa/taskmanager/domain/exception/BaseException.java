package cl.nuevo.spa.taskmanager.domain.exception;

import java.util.Arrays;
import lombok.Data;

@Data
public class BaseException extends RuntimeException {

  private final String errorCode;
  private final String message;
  private final String debugMessage;
  private final int httpStatusCode;

  public BaseException(String message, Throwable cause, String errorCode, int httpStatusCode) {
    super(message, cause);
    this.errorCode = errorCode;
    this.message = message;
    this.debugMessage = Arrays.toString(cause.getStackTrace());
    this.httpStatusCode = httpStatusCode;
  }
}
