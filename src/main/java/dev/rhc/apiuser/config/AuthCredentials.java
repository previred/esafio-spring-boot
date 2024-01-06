package dev.rhc.apiuser.config;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
