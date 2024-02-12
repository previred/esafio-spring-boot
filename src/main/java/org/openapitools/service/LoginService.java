package org.openapitools.service;

import org.openapitools.dto.Login;
import org.openapitools.dto.TokenResponse;

public interface LoginService {

    TokenResponse login(Login request);
}
