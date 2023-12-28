package com.nuevospa.task.management.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nuevospa.task.management.util.MessageErrorsConstants;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidatorImpl.class)
public @interface EmailValidator {
    String message() default MessageErrorsConstants.ERROR_BAD_EMAIL;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
