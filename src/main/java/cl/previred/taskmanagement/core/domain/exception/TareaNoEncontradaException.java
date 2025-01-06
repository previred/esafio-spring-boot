package cl.previred.taskmanagement.core.domain.exception;

public class TareaNoEncontradaException extends RuntimeException{

    public TareaNoEncontradaException(String mensaje){
        super(mensaje);
    }

}
