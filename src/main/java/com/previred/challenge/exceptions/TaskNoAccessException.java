package com.previred.challenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskNoAccessException extends RuntimeException {

    private Integer userId;
    private Integer taskId;

}
