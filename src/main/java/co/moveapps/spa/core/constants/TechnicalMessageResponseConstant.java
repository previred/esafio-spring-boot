
package co.moveapps.spa.core.constants;

import co.moveapps.spa.core.controller.dto.BaseResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author andresduran0205@gmail.com
 */
@NoArgsConstructor
public class TechnicalMessageResponseConstant {

    public static final BaseResponse EXCEPTION_UNAUTHORIZED = message(HttpStatus.UNAUTHORIZED, "You are not authorized to access this resource.");
    public static final BaseResponse EXCEPTION_BAD_REQUEST_VALIDATE_STRING_SANITIZE = message(HttpStatus.BAD_REQUEST, "The entered parameter has invalid characters, please try again.");
    public static final BaseResponse EXCEPTION_NOT_FOUND_DATA = message(HttpStatus.NOT_FOUND, "Not exist data associated.");
    public static final BaseResponse EXCEPTION_VALIDATION_STRING_SANITIZE = message(HttpStatus.BAD_REQUEST, "The parameter value is not valid for a system.");

    private static BaseResponse message(HttpStatus status, String message) {
        return BaseResponse.builder(status, message).build();
    }

    public static BaseResponse message(String message) {
        return TechnicalMessageResponseConstant.message(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
