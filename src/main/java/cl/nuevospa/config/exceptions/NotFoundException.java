package cl.nuevospa.config.exceptions;

import cl.nuevospa.config.exceptions.errors.ErrorCode;

public class NotFoundException extends GenericException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8135942694785690934L;

    
    
}
