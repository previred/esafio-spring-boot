package co.moveapps.spa.core.service.auth;

import co.moveapps.spa.core.config.security.dto.CurrentUserPrincipal;
import co.moveapps.spa.core.controller.model.CredentialsResponse;
import co.moveapps.spa.core.exception.BusinessException;

public interface AuthenticationService {

    CredentialsResponse authentication(final String username, final String password) throws BusinessException;

    CurrentUserPrincipal validate(final String token) throws BusinessException;
}
