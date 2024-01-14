package jlillor.ms.tasks.manager.repositories;

import jlillor.ms.tasks.manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de usuarios.
 *
 * @see JpaRepository
 * @see User
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // --------------------------------------------------------------------------------------
    // -- Métodos Customs -------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    /**
     * Busca un usuario por su email.
     *
     * @param email {@link String} email del usuario
     * @return usuario {@link User} usuario encontrado
     */
    Optional<User> findByEmail(String email);

}
