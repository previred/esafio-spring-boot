package com.previred.desafiobackend.domain.exception.task;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

public class NonValidStatusChangeException extends RuntimeException {

    public NonValidStatusChangeException(String message) {
        super(message);
    }

    public static NonValidStatusChangeException thrown(){
        throw new NonValidStatusChangeException("New Status not valid from the current task status.");
    }
}
