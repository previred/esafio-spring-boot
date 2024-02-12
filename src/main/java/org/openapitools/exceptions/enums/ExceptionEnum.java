package org.openapitools.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openapitools.exceptions.IExceptionEnum;

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements IExceptionEnum {

    USER_NOT_FOUND("Credenciales incorrectas"),
    TASK_NOT_FOUND("La tarea no esta registrada"),
    FORBIDDEN("");
    private final String name;

}

