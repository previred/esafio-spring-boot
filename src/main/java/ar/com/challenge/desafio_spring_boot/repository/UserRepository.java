package ar.com.challenge.desafio_spring_boot.repository;

import ar.com.challenge.desafio_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
