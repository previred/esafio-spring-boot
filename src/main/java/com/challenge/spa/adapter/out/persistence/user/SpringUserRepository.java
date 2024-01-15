package com.challenge.spa.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<UserEntity, String> {
   Optional<UserEntity> findByUsername(String username);
}
