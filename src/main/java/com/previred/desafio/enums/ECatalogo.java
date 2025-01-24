/*
 * @(#)ECatalogo.java
 *
 * Copyright (c) BANCO DE CHILE (Chile). All rights reserved.
 *
 * All rights to this product are owned by BANCO DE CHILE and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by BANCO DE CHILE.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package com.previred.desafio.enums;

/**
 * ECatalogo.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */
public enum ECatalogo {

    USUARIO_NO_EXISTE("E001", "El Usuario no existe."),
    CONTRASENA_INVALIDA("E002", "La contraseña es inválida."),
    ERROR_TOKEN("E003", "Token de autorización inválido o expirado."),
    ERROR_ID_CLIENTE("E004", "ID de cliente no encontrado."),
    ERROR_DESCONOCIDO("E100", "Ha ocurrido un error inesperado.");

    private final String code;
    private final String message;

    ECatalogo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}