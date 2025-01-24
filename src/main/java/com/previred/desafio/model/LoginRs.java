package com.previred.desafio.model;

import lombok.Data;

/**
 * LoginRs.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Data
public class LoginRs {
    private final String token;

    public LoginRs(String token) {
        this.token = token;
    }
}
