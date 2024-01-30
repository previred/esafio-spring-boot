package cl.nuevo.spa.desafio.repository;


import java.util.Optional;

import cl.nuevo.spa.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

}
