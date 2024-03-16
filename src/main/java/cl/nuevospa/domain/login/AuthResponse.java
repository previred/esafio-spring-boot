package cl.nuevospa.domain.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private Integer status;
    private String token;
}
