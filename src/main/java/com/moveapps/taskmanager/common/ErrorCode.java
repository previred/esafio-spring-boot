package com.moveapps.taskmanager.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("U_NF_0001"),
    TASK_NOT_FOUND("T_NF_0001"),
    GENERAL("G_0001"),
    INVALID_CREDENTIALS("IC_0001");

    String value;
}
