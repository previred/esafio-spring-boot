package com.previred.challenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskNotFoundException extends RuntimeException {

    private Integer id;

}
