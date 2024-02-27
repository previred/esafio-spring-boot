package cl.tecnova.pruebatecnica.services;

import cl.tecnova.pruebatecnica.dto.LoginUserRequest;
import cl.tecnova.pruebatecnica.dto.TokenResponse;
import cl.tecnova.pruebatecnica.exception.HTTPException;

public interface UsuariosService {

    TokenResponse login(LoginUserRequest request) throws HTTPException;

}
