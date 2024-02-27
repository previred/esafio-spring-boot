package cl.tecnova.pruebatecnica.repositories;

import cl.tecnova.pruebatecnica.model.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Integer> {

    boolean existsByUsernameIgnoreCaseAndPassword(String username, String password);

}
