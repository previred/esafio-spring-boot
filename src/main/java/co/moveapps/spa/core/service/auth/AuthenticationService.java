package co.moveapps.spa.core.service.auth;

import co.moveapps.spa.core.config.security.dto.CurrentUserPrincipal;
import co.moveapps.spa.core.exception.BusinessException;

import java.util.Map;

public interface AuthenticationService {

    Map<String, Object> authentication(final String username, final String password) throws BusinessException;

    CurrentUserPrincipal validate(final String token) throws BusinessException;
}
