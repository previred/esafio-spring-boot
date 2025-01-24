package cl.rreyes.nuevospa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.rreyes.nuevospa.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
