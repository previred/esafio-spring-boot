package org.openapitools.repository;

import org.openapitools.dto.Login;
import org.openapitools.dto.UserLogin;

import java.util.Optional;

public interface LoginRepository {

    Optional<UserLogin> findByEmailAndPassword(Login login);
}
