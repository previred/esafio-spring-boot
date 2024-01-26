package com.test.previred.infrastructure.adapter.h2.enums;

import lombok.Getter;

@Getter
public enum TaskStatusEnum {
    PENDING("Pendiente"),
    IN_PROGRESS("En Progreso"),
    COMPLETED("Completada");

    private final String label;

    TaskStatusEnum(String label) {
        this.label = label;
    }

}