package cl.dpichinil.desafio.desafiospringboot.config.exception;

public class AuthenticationException extends RuntimeException {
    private int code;
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
    public AuthenticationException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
