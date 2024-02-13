package cl.nuevospa.taskmanagement.exception;

public class TaskStateException extends RuntimeException {
    public TaskStateException(String message) {
        super(message);
    }
}