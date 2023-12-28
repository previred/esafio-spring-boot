package com.nuevospa.task.management.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, String> {

    @Override
    public void initialize(PasswordValidator constraint) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
    	if (password == null || password.trim().isEmpty()) {
    		context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El campo password no puede estar vacía").addConstraintViolation();
            return false;
        }
    	
        return validPasword(password);
    }
    
    
    private static boolean validPasword(String password) {
    	
    	String exp = "^(?=(?:[^A-Z]*[A-Z]){1})(?=(?:[^0-9]*[0-9]){2})(?=(?:[^a-z]*[a-z]){2})[a-zA-Z0-9]{8,12}$";
        return password.matches(exp);
    }

	

}
