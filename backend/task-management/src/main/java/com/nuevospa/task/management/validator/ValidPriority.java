package com.nuevospa.task.management.validator;

import com.nuevospa.task.management.dto.task.Priority;
import com.nuevospa.task.management.exception.ValidationPriorityException;
import com.nuevospa.task.management.util.CodesErrorsConstants;
import com.nuevospa.task.management.util.MessageErrorsConstants;

import java.util.Arrays;

public class ValidPriority {

	public static Priority validPriority(String value) {
        return Arrays.stream(Priority.values())
                .filter(priority -> priority.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ValidationPriorityException(MessageErrorsConstants.BAD_PRIORITY_EXCEPTION_MESSAGE,
                		CodesErrorsConstants.BAD_PASSWORD_EXCEPTION_CODE));
    }

}
