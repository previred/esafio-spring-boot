package cl.previred.taskmanagement.core.domain.exception;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String campo) {
        super("Usuario " + campo + " no encontrado.");
    }
}
