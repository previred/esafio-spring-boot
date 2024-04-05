
package co.moveapps.spa.core.constants;

import co.moveapps.spa.core.controller.dto.BaseResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author andresduran0205@gmail.com
 */
@NoArgsConstructor
public class MessageResponseConstant {

    public static final BaseResponse EXCEPTION_AUTHENTICATION_USER_FAILED = message(HttpStatus.UNAUTHORIZED, "The credentials entered do not exist or do not match, please try again.");

    public static final BaseResponse EXCEPTION_NOT_DATA_FOUND = message(HttpStatus.NOT_FOUND, "The resource not exist, please try again with another value.");


    private static BaseResponse message(HttpStatus status, String message) {
        return BaseResponse.builder(status, message).build();
    }

    public static BaseResponse message(String message) {
        return MessageResponseConstant.message(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
