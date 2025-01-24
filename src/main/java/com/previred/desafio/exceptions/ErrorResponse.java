package com.previred.desafio.exceptions;

/**
 * ErrorResponse.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

public class ErrorResponse {

    private String codigo;
    private String mensaje;

    public ErrorResponse(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
