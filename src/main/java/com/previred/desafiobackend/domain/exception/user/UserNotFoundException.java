package com.previred.desafiobackend.domain.exception.user;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException thrown() {
        throw new UserNotFoundException("User not found.");
    }
}
