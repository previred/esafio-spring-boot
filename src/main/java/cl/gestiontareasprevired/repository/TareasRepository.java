package cl.gestiontareasprevired.repository;

import cl.gestiontareasprevired.model.Tarea;
import cl.gestiontareasprevired.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareasRepository extends JpaRepository<Tarea, String> {
    List<Tarea> findByUsuario_Email(String email);

    Optional<Tarea> findByTituloTareaAndUsuario_Email(String tituloTarea, String email);

}