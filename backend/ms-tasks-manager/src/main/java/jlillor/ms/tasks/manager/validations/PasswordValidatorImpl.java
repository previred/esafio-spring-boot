package jlillor.ms.tasks.manager.validations;

import jlillor.ms.tasks.manager.properties.RegexpProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Clase que implementa la validación de la contraseña.
 *
 * @see RegexpProperty
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@RequiredArgsConstructor
public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, String> {

    /** Propiedad con las expresiones regulares */
    private final RegexpProperty regexpProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos sobreescritos ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(PasswordValidator constraint) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
    	if (ObjectUtils.isEmpty(password)) {
    		context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password cannot be empty.").addConstraintViolation();
            return false;
        }
        return password.matches(regexpProperty.getPassword());
    }

}
