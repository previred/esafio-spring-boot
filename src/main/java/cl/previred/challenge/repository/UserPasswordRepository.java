package cl.previred.challenge.repository;

import cl.previred.challenge.repository.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Integer> {

    Optional<UserPassword> findByUserId(Integer id);
}
