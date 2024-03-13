package cl.gestiontareasprevired.repository;

import cl.gestiontareasprevired.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, String> {
    Optional<Usuarios> findByEmailAndPassword(String email, String password);

    Optional<Usuarios> findByEmail(String email);


}