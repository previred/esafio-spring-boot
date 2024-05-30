package com.nuevospa.taskmanager.exceptions.enums;

import lombok.*;
import com.nuevospa.taskmanager.exceptions.IExceptionEnum;

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements IExceptionEnum {

    USER_NOT_FOUND("usuario o contraseña incorrecta"),
    TASK_NOT_FOUND("Tarea no encontrada"),
    FORBIDDEN("");
    private final String name;

}

