package com.nuevospa.gestiontareas.data;

import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class EstadosTareasRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EstadosTareasRepository estadosTareasRepository;

    @Test
    void findByNombre_WhenExists() {
        EstadoTarea estadoTarea = EstadoTarea.builder()
                .nombre("En Progreso")
                .build();
        entityManager.persistAndFlush(estadoTarea);

        Optional<EstadoTarea> found = estadosTareasRepository.findByNombre("En Progreso");
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNombre()).isEqualTo(estadoTarea.getNombre());
    }

    @Test
    void findByNombre_WhenNotExists() {
        Optional<EstadoTarea> found = estadosTareasRepository.findByNombre("No Existe");
        assertThat(found.isPresent()).isFalse();
    }

    @Test
    void findById_WhenExists() {
        EstadoTarea estadoTarea = EstadoTarea.builder()
                .nombre("Completado")
                .build();
        estadoTarea = entityManager.persistFlushFind(estadoTarea);

        Optional<EstadoTarea> found = estadosTareasRepository.findById(estadoTarea.getId());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getNombre()).isEqualTo(estadoTarea.getNombre());
    }

    @Test
    void findById_WhenNotExists() {
        Optional<EstadoTarea> found = estadosTareasRepository.findById(999L);
        assertThat(found.isPresent()).isFalse();
    }
}
