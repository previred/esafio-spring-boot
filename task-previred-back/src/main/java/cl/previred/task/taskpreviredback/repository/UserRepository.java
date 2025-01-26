package cl.previred.task.taskpreviredback.repository;

import cl.previred.task.taskpreviredback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT s from User s WHERE s.username= ?1")
    Optional<User> findUserByUserName(String username);
}
