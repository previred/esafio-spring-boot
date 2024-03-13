package cl.gestiontareasprevired.service;

import cl.gestiontareasprevired.dto.LoginCredentials;
import cl.gestiontareasprevired.dto.LoginSuccessResponse;

import java.util.Optional;

public interface UsuariosService {
    Optional<LoginSuccessResponse> validarUsuario(LoginCredentials loginCredentials);

}
