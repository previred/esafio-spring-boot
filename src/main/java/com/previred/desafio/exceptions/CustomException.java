package com.previred.desafio.exceptions;

/**
 * CustomException.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

public class CustomException extends RuntimeException {

    private final String codigo;
    private final String mensaje;

    public CustomException(String codigo, String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public CustomException(String codigo, String mensaje, Throwable t) {
        super(mensaje, t);
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
