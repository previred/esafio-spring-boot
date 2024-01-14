package jlillor.ms.tasks.manager.services;

import jlillor.ms.tasks.manager.entities.User;

/**
 * Servicio de usuarios.
 *
 * @author Juan Lillo
 * @version 1.0
 * @see User
 * @since 1.0
 */
public interface UserService {

    // -----------------------------------------------------------------------------------------
    // -- Métodos a implementar ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Obtiene un usuario por su id.
     *
     * @param token {@link String} token del usuario
     * @return {@link User} usuario encontrado
     */
    User getUserByToken(String token);

    /**
     * Obtiene un usuario por su email.
     *
     * @param email {@link String} email del usuario
     * @return {@link User} usuario encontrado
     */
    User getUserByEmail(String email);

    /**
     * Edita un usuario ya creado.
     *
     * @param user {@link User} usuario a editar
     */
    void edit(User user);

}
