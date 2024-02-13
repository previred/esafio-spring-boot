package com.moveapps.management.user.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moveapps.management.user.infraestructure.entities.UserEntity;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	Optional<UserEntity> findByUsername(String username);

}