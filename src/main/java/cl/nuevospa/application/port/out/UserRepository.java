package cl.nuevospa.application.port.out;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.nuevospa.domain.h2.entities.UserDTO;

public interface UserRepository  extends  CrudRepository <UserDTO,Integer>{
    Optional<UserDTO> findByUsername(String username);
}
