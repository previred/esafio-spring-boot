package cl.gestiontareasprevired.exception;

public class TareaDuplicadaException extends RuntimeException {
    public TareaDuplicadaException(String message) {
        super(message);
    }
}