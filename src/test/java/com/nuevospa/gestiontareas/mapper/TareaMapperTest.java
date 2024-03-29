package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.model.security.Usuario;
import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import com.nuevospa.gestiontareas.model.tareas.Tarea;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class TareaMapperTest {

    @Test
    void dtoToEntitySingleConversion() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().id(1L).build();
        EstadoTareaDTO estadoTareaDTO = EstadoTareaDTO.builder().id(1L).nombre("En progreso").build();
        TareaDTO tareaDTO = TareaDTO.builder()
                .id(1L)
                .descripcion("Tarea de prueba")
                .usuario(usuarioDTO)
                .estadoTarea(estadoTareaDTO)
                .build();

        Tarea tarea = TareaMapper.dtoToEntity(tareaDTO);

        assertNotNull(tarea);
        assertEquals(tareaDTO.getId(), tarea.getId());
        assertEquals(tareaDTO.getDescripcion(), tarea.getDescripcion());
        assertNotNull(tarea.getUsuario());
        assertNotNull(tarea.getEstadoTarea());
    }

    @Test
    void entityToDtoSingleConversion() {
        Usuario usuario = Usuario.builder().id(1L).build();
        EstadoTarea estadoTarea = EstadoTarea.builder().id(1L).nombre("En progreso").build();
        Tarea tarea = Tarea.builder()
                .id(1L)
                .descripcion("Tarea de prueba")
                .usuario(usuario)
                .estadoTarea(estadoTarea)
                .build();

        TareaDTO tareaDTO = TareaMapper.entityToDto(tarea);

        assertNotNull(tareaDTO);
        assertEquals(tarea.getId(), tareaDTO.getId());
        assertEquals(tarea.getDescripcion(), tareaDTO.getDescripcion());
        assertNotNull(tareaDTO.getUsuario());
        assertNotNull(tareaDTO.getEstadoTarea());
    }

    @Test
    void dtoToEntitySetConversion() {
        Set<TareaDTO> tareaDTOs = new HashSet<>();
        tareaDTOs.add(TareaDTO.builder().id(1L).descripcion("Tarea 1").build());
        tareaDTOs.add(TareaDTO.builder().id(2L).descripcion("Tarea 2").build());

        Set<Tarea> tareas = TareaMapper.dtoToEntity(tareaDTOs);

        assertNotNull(tareas);
        assertEquals(2, tareas.size());
    }

    @Test
    void entityToDtoSetConversion() {
        Set<Tarea> tareas = new HashSet<>();
        tareas.add(Tarea.builder().id(1L).descripcion("Tarea 1").build());
        tareas.add(Tarea.builder().id(2L).descripcion("Tarea 2").build());

        Set<TareaDTO> tareaDTOs = TareaMapper.entityToDto(tareas);

        assertNotNull(tareaDTOs);
        assertEquals(2, tareaDTOs.size());
    }

    @Test
    void nullInputReturnsNullOrEmptySet() {
        assertNull(TareaMapper.dtoToEntity((TareaDTO) null));
        assertNull(TareaMapper.entityToDto((Tarea) null));
        assertTrue(TareaMapper.dtoToEntity((Set<TareaDTO>) null).isEmpty());
        assertTrue(TareaMapper.entityToDto((Set<Tarea>) null).isEmpty());
    }
}
