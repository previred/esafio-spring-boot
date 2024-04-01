package cl.previred.desafip.msprevireddesafio.repositories;


import cl.previred.desafip.msprevireddesafio.DTO.LoginRequest;
import cl.previred.desafip.msprevireddesafio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
