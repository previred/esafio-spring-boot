package com.moveapps.tasks.enums;

public enum Status {
    TODO("Por Hacer"),
    IN_PROGRESS("En Progreso"),
    IN_REVIEW("En Revisión"),
    COMPLETED("Completada"),
    BLOCKED("Bloqueada"),
    ON_HOLD("En Espera"),
    CANCELLED("Cancelada");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
