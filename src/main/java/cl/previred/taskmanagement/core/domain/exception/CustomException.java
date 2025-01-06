package cl.previred.taskmanagement.core.domain.exception;

public class CustomException extends RuntimeException{

    public CustomException(String mensaje){
        super(mensaje);
    }

}
