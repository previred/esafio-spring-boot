
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

    public static final BaseResponse EXCEPTION_USER_NOT_FOUND = message(HttpStatus.BAD_REQUEST, "The client id not exist, please try again with another value.");
    public static final BaseResponse EXCEPTION_STATUS_NOT_FOUND = message(HttpStatus.BAD_REQUEST, "The status id not exist, please try again with another value.");
    public static final BaseResponse EXCEPTION_TASK_NOT_FOUND = message(HttpStatus.BAD_REQUEST, "The task id not exist, please try again with another value.");

    public static final BaseResponse EXCEPTION_USERNAME_ALREADY_REGISTER = message(HttpStatus.BAD_REQUEST, "The username already exist, please try again with another value.");



    private static BaseResponse message(HttpStatus status, String message) {
        return BaseResponse.builder(status, message).build();
    }

    public static BaseResponse message(String message) {
        return MessageResponseConstant.message(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
