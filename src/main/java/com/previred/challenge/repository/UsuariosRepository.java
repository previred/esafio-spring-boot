package com.previred.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.previred.challenge.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
	@Query("SELECT u FROM Usuarios u WHERE u.username = :username")
	Usuarios findByUsername(@Param("username") String username);
}
