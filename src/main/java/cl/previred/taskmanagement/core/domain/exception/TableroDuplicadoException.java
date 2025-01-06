package cl.previred.taskmanagement.core.domain.exception;

public class TableroDuplicadoException extends RuntimeException {

    public TableroDuplicadoException(String mensaje){
        super(mensaje);
    }
}
