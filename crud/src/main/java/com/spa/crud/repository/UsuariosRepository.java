package com.spa.crud.repository;

import com.spa.crud.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM Usuarios u WHERE u.username = :username")
    Usuarios getByUsername(@Param("username") String username);

    @Query("SELECT u.idUsuario FROM Usuarios u WHERE u.username = :username")
    Long getIdByUsername(@Param("username") String username);

    void deleteByIdUsuario(@Param("idUsuario") Long idUsuario);
}
