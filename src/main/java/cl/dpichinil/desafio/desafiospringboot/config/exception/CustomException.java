package cl.dpichinil.desafio.desafiospringboot.config.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    HttpStatus status;
    private int code;

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public CustomException(HttpStatus status, int code) {
        this.status = status;
        this.code = code;
    }
    public CustomException(HttpStatus status, int code, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }
}
