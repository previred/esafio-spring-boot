package jlillor.ms.tasks.manager.validations;

import jlillor.ms.tasks.manager.properties.RegexpProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Clase que implementa la validación del email.
 *
 * @see RegexpProperty
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@RequiredArgsConstructor
public class EmailValidatorImpl implements ConstraintValidator<EmailValidator, String> {

    /** The regexp property. */
    private final RegexpProperty regexpProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos sobreescritos ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(EmailValidator constraint) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email cannot be empty.").addConstraintViolation();
            return false;
        }
        return email.matches(regexpProperty.getEmail());
    }

}
