package com.nuevoapp.prueba.domain.repository;

import com.nuevoapp.prueba.domain.model.entity.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<PasswordEntity, String>{
}
