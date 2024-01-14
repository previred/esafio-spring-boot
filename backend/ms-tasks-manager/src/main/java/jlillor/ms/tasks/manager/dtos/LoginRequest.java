package jlillor.ms.tasks.manager.dtos;

import jlillor.ms.tasks.manager.validations.EmailValidator;
import jlillor.ms.tasks.manager.validations.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * The Class LoginRequest.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Data
@Builder
@AllArgsConstructor
public class LoginRequest {

    /** The email. */
    @EmailValidator
    @Size(max = 256, message = "Email must not be longer than 256 characters long.")
    private String email;
    /** The password. */
    @PasswordValidator
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

}
