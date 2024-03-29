package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class EstadoTareaMapperTest {

    @Test
    void whenEntityToDto_thenCorrect() {
        EstadoTarea estadoTarea = EstadoTarea.builder()
                .id(1L)
                .nombre("En Progreso")
                .build();

        EstadoTareaDTO estadoTareaDTO = EstadoTareaMapper.entityToDto(estadoTarea);

        assertThat(estadoTareaDTO).isNotNull();
        assertThat(estadoTareaDTO.getId()).isEqualTo(estadoTarea.getId());
        assertThat(estadoTareaDTO.getNombre()).isEqualTo(estadoTarea.getNombre());
    }

    @Test
    void whenDtoToEntity_thenCorrect() {
        EstadoTareaDTO estadoTareaDTO = EstadoTareaDTO.builder()
                .id(2L)
                .nombre("Completado")
                .build();

        EstadoTarea estadoTarea = EstadoTareaMapper.dtoToEntity(estadoTareaDTO);

        assertThat(estadoTarea).isNotNull();
        assertThat(estadoTarea.getId()).isEqualTo(estadoTareaDTO.getId());
        assertThat(estadoTarea.getNombre()).isEqualTo(estadoTareaDTO.getNombre());
    }

    @Test
    void whenEntityToDtoWithNullEntity_thenNullDto() {
        assertNull(EstadoTareaMapper.entityToDto(null));
    }

    @Test
    void whenDtoToEntityWithNullDto_thenNullEntity() {
        assertNull(EstadoTareaMapper.dtoToEntity(null));
    }
}
