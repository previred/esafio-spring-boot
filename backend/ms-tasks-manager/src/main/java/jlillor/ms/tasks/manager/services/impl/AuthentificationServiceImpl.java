package jlillor.ms.tasks.manager.services.impl;

import jlillor.ms.tasks.manager.dtos.LoginRequest;
import jlillor.ms.tasks.manager.exceptions.MsBadRequestException;
import jlillor.ms.tasks.manager.exceptions.MsInternalError;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.services.AuthentificationService;
import jlillor.ms.tasks.manager.services.UserService;
import jlillor.ms.tasks.manager.utils.SessionTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementación de la interfaz de servicio de autentificación.
 *
 * @see AuthentificationService
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthentificationService {

    /** Codificador de contraseñas. */
    private final BCryptPasswordEncoder bCryptEncoder;
    /** Utilidad de tokens de sesión. */
    private final SessionTokenUtil sessionTokenUtil;
    /** Servicio de usuarios. */
    private final UserService userService;
    /** Propiedades de mensajes. */
    private final MessageProperty msgProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos sobreescritos ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String login(final LoginRequest loginData) {
        final var user = userService.getUserByEmail(loginData.getEmail());
        if (!bCryptEncoder.matches(loginData.getPassword(), user.getPassword())) {
            throw new MsBadRequestException(msgProperty.getInvalidLogin());
        }
        try {
            user.setLastLogin(LocalDateTime.now());
            userService.edit(user);
            return sessionTokenUtil.generateToken(user.getEmail());
        } catch (Exception e) {
            throw new MsInternalError(msgProperty.getGenericError());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(String token) {
        final var emailToken = sessionTokenUtil.getEmail(token);
        if (!sessionTokenUtil.validateToken(token, emailToken)) {
            throw new MsBadRequestException(msgProperty.getInvalidToken());
        }
        userService.getUserByEmail(emailToken);
    }

}
