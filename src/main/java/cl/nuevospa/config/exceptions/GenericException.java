package cl.nuevospa.config.exceptions;

import cl.nuevospa.config.exceptions.errors.ErrorCode;
import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    private ErrorCode errorCode;
    /**
     * 
     */
    private static final long serialVersionUID = -89426292260695755L;

    GenericException(ErrorCode errorCode){
        super(errorCode.message());
        this.errorCode=errorCode;
    }
    
    
}
