package cl.dpichinil.desafio.desafiospringboot.persistence.repository;

import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
