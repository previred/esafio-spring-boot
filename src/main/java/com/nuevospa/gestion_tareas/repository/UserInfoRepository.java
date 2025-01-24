package com.nuevospa.gestion_tareas.repository;

import com.nuevospa.gestion_tareas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username); // Use 'email' if that is the correct field for login
}
