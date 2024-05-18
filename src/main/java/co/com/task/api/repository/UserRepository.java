package co.com.task.api.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.task.api.domain.User;

public interface UserRepository extends CrudRepository<User, UUID> {

    @Query(value = "SELECT * FROM users where email=?1 and password=?2", nativeQuery = true)
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM users where email=?1", nativeQuery = true)
    Optional<User> getUserByEmail(String email);
}
