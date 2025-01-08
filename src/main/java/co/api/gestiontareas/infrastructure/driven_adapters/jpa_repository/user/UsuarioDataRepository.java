package co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.user;

import co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.user.entities.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UsuarioDataRepository extends CrudRepository<UsuarioEntity, Long>, QueryByExampleExecutor<UsuarioEntity> {
    boolean existsByUsername(String username);
    UsuarioEntity findByUsername(String username);
}
