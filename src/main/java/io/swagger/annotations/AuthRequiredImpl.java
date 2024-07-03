package io.swagger.annotations;

import io.swagger.entity.UserEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

@Component(value = "authRequired")
public class AuthRequiredImpl {
    public boolean validate(AbstractAuthenticationToken authentication) {
        return authentication.getPrincipal() instanceof UserEntity;
    }
}
