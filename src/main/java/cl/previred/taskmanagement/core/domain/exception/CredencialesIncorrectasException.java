package cl.previred.taskmanagement.core.domain.exception;

public class CredencialesIncorrectasException extends RuntimeException{

    public CredencialesIncorrectasException(String mensaje){
        super(mensaje);
    }

}
