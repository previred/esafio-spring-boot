package cl.previred.taskmanagement.core.domain.exception;

public class TableroNoEncontradoException extends RuntimeException{

    public TableroNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
