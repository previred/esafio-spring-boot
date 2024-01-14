package jlillor.ms.tasks.manager.services;

import jlillor.ms.tasks.manager.dtos.LoginRequest;

/**
 * Servicio para la gestión de autenticación.
 *
 * @author Juan Lillo
 * @version 1.0
 * @see LoginRequest
 * @see String
 * @since 1.0
 */
public interface AuthentificationService {


    // -----------------------------------------------------------------------------------------
    // -- Métodos a implementar ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Realiza el login de un usuario.
     *
     * @param user {@link LoginRequest} usuario a loguear
     * @return {@link String} token del usuario
     */
    String login(LoginRequest user);

    /**
     * Valida un token.
     *
     * @param token {@link String} token a validar
     */
    void validate(String token);

}
