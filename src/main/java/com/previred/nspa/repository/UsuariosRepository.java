package com.previred.nspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.previred.nspa.entity.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByNombreUsuario(String nombreUsuario);
    Usuarios findByCorreoElectronico(String correoElectronico);
}

