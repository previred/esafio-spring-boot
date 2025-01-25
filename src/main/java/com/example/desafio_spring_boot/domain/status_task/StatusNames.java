package com.example.desafio_spring_boot.domain.status_task;

public enum StatusNames {
    CREATED("CREATED"),
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    StatusNames(String string) {
    }

    public static StatusNames fromString(String statusName) {
        for (StatusNames status : StatusNames.values()) {
            if (status.toString().equalsIgnoreCase(statusName)) {
                return status;
            }
        }
        return null;
    }

    public static String allowedValues() {
        StringBuilder allowedValues = new StringBuilder();
        for (StatusNames status : StatusNames.values()) {
            allowedValues.append(status.name()).append(", ");
        }
        return allowedValues.substring(0, allowedValues.length() - 2);
    }

}
