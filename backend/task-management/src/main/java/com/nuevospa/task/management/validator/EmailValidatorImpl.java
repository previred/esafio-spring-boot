package com.nuevospa.task.management.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidatorImpl implements ConstraintValidator<EmailValidator, String> {

    @Override
    public void initialize(EmailValidator constraint) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
    	
    	if (email == null || email.trim().isEmpty()) {
    		context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El campo email no puede estar vacía").addConstraintViolation();
            return false;
        }
    	
    	final String exp = "^[a-z0-9!#$%&'*+/=?^_`{|}~\\-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~\\-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        return email.matches(exp) && email != null;
    }

	

}
