package jlillor.ms.tasks.manager.services.impl;

import jlillor.ms.tasks.manager.entities.User;
import jlillor.ms.tasks.manager.exceptions.MsInternalError;
import jlillor.ms.tasks.manager.exceptions.MsNotFoundException;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.repositories.UserRepository;
import jlillor.ms.tasks.manager.services.UserService;
import jlillor.ms.tasks.manager.utils.SessionTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz de servicio de usuarios.
 *
 * @see UserService
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /** Repositorio de usuarios. */
    private final UserRepository userRepository;
    /** Utilidad de tokens de sesión. */
    private final SessionTokenUtil sessionTokenUtil;
    /** Propiedades de mensajes. */
    private final MessageProperty msgProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos sobreescritos ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByEmail(final String email) {
        final var validUser = userRepository.findByEmail(email);
        if (validUser.isEmpty()) {
            throw new MsNotFoundException(msgProperty.getGenericNotFound());
        }
        return validUser.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByToken(final String token) {
        final var validUser = userRepository.findByEmail(sessionTokenUtil.getEmail(token));
        if (validUser.isEmpty()) {
            throw new MsNotFoundException(msgProperty.getGenericNotFound());
        }
        return validUser.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edit(final User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new MsInternalError(msgProperty.getGenericError());
        }
    }

}
