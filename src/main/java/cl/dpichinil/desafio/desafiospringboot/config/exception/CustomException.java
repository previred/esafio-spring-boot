package cl.dpichinil.desafio.desafiospringboot.config.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    HttpStatus status;
    private int code;
    private String module;

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getModule() {
        return module;
    }

    public CustomException(HttpStatus status, int code, String module) {
        this.status = status;
        this.code = code;
        this.module = module;
    }
    public CustomException(HttpStatus status, int code, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }
}
