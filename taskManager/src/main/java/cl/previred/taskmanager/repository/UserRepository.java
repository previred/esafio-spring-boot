package cl.previred.taskmanager.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.previred.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}