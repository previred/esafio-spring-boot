package cl.previred.challenge.service;

public class TaskReadOnlyException extends RuntimeException {

    private static final String defaultMessage = "Usuario no es editor de esta tarea";

    public TaskReadOnlyException() {
        super(defaultMessage);
    }

    public TaskReadOnlyException(String msg) {
        super(msg);
    }
}
