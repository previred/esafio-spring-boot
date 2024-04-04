package co.moveapps.spa.core.model.repository;

import co.moveapps.spa.core.model.entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAuthUserEntityRepository extends JpaRepository<AuthUserEntity, UUID> {

    Optional<AuthUserEntity> findByEmailAndPassword(final String username, final String password);
}
