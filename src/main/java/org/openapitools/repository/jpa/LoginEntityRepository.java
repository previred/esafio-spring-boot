package org.openapitools.repository.jpa;

import org.openapitools.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginEntityRepository extends JpaRepository<LoginEntity, UUID> {
    Optional<LoginEntity> findByEmailAndPassword(String email, String password);
}
