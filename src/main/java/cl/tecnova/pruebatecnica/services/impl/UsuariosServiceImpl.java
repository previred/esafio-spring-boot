package cl.tecnova.pruebatecnica.services.impl;

import cl.tecnova.pruebatecnica.config.JWTAuthtenticationConfig;
import cl.tecnova.pruebatecnica.dto.LoginUserRequest;
import cl.tecnova.pruebatecnica.dto.TokenResponse;
import cl.tecnova.pruebatecnica.exception.HTTPException;
import cl.tecnova.pruebatecnica.repositories.UsuariosRepository;
import cl.tecnova.pruebatecnica.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtentication;

    @Override
    public TokenResponse login(LoginUserRequest request) throws HTTPException {
        if (!StringUtils.hasText(request.getUsername()) || !StringUtils.hasText(request.getPassword())) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "Debe ingresar Username y Password.");
        }
        if (!usuariosRepository.existsByUsernameIgnoreCaseAndPassword(request.getUsername(), request.getPassword())) {
            throw new HTTPException(HttpStatus.UNAUTHORIZED, "Username y/o password incorrectos.");
        }

        return new TokenResponse(request.getUsername(), jwtAuthtentication.getJWTToken(request.getUsername()));
    }

}
