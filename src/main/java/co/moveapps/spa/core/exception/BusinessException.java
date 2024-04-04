package co.moveapps.spa.core.exception;

import co.moveapps.spa.core.controller.dto.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends Exception {

    private BaseResponse response;

    public BusinessException(BaseResponse exception) {
        super(exception.getMessage());
        this.response = exception;
    }

    public BusinessException(String message) {
        super(message);
    }

}
