package cl.nuevospa.domain.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentials {
    private String username;
    private String password;
}
