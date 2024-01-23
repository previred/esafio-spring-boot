package cl.nuevo.spa.taskmanager.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundTaskException extends BaseException {

  private static final String NO_FOUND_TASK_EXCEPTION = "No task was found for the provided id: %s";

  public NotFoundTaskException(String taskId, String errorCode) {
    super(
        String.format(NO_FOUND_TASK_EXCEPTION, taskId),
        new Exception(),
        errorCode,
        HttpStatus.NOT_FOUND.value());
  }
}
