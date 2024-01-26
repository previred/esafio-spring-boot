package com.test.previred.infrastructure.adapter.h2.task;

public class Exceptions extends RuntimeException {
    public Exceptions(String message) {
        super(message);
    }

    public static class TaskNotFoundException extends Exceptions {
        public TaskNotFoundException(String id) {
            super("No se encontró la tarea con ID: " + id);
        }
    }

    public static class TaskCreationException extends Exceptions {
        public TaskCreationException(String message) {
            super("Error al crear la tarea: " + message);
        }
    }
}
