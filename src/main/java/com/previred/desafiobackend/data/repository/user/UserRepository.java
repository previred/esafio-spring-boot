package com.previred.desafiobackend.data.repository.user;

import com.previred.desafiobackend.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAll();

    User save(User user);

}
