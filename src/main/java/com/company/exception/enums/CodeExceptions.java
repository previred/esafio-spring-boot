package com.company.exception.enums;

public enum CodeExceptions {


    NOT_AUTHORIZED("SYS0001"),
    TASK_NOT_FOUND("T0001"),
    TASK_STATUS_NOT_FOUND("TS0001"),
    USER_NOT_FOUND("US0001");


    private String value;

    private CodeExceptions(final String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
