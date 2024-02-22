package com.previred.challenge.services;

import com.previred.challenge.model.RegisteredUser;
import org.springframework.security.core.Authentication;

public interface JWTService {

    /**
     * Check if token is valid.
     *
     * @param token jwt token to be checked.
     * @param registeredUser claimed user to be checked.
     * @return true when user and token match.
     */
    boolean validateJwtToken(String token, RegisteredUser registeredUser);

    /**
     * Retrieve user from token
     * @param token
     * @return the user email.
     */
    String getUsernameFromToken(String token);

    /**
     *
     * @param registeredUser
     * @return
     */
    String generateToken(RegisteredUser registeredUser);

}
