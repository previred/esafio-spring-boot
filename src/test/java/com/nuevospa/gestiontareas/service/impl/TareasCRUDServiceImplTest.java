package com.nuevospa.gestiontareas.service.impl;

import com.nuevospa.gestiontareas.data.EstadosTareasRepository;
import com.nuevospa.gestiontareas.data.TareaRepository;
import com.nuevospa.gestiontareas.data.UsuariosRepository;
import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.exception.NotFoundException;
import com.nuevospa.gestiontareas.model.tareas.Tarea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TareasCRUDServiceImplTest {
    @Mock
    private TareaRepository tareaRepository;
    @Mock
    private EstadosTareasRepository estadosTareasRepository;
    @Mock
    private UsuariosRepository usuariosRepository;
    @InjectMocks
    private TareasCRUDServiceImpl tareasCRUDService;

    @Test
    void crearTareaTest() {
        TareaDTO tareaDTO = TareaDTO.builder().descripcion("Nueva Tarea").build();
        Tarea tarea = Tarea.builder().id(1L).descripcion("Nueva Tarea").build();

        given(tareaRepository.save(any(Tarea.class))).willReturn(tarea);

        TareaDTO resultado = tareasCRUDService.crearTarea(tareaDTO);

        assertNotNull(resultado);
        assertEquals(tarea.getDescripcion(), resultado.getDescripcion());
    }

    @Test
    void obtenerTareaPorIdTest_WhenFound() throws NotFoundException {
        Tarea tarea = Tarea.builder().id(1L).descripcion("Tarea existente").build();
        given(tareaRepository.findById(1L)).willReturn(Optional.of(tarea));

        TareaDTO resultado = tareasCRUDService.obtenerTareaPorId(1L);

        assertNotNull(resultado);
        assertEquals(tarea.getId(), resultado.getId());
    }

    @Test
    void obtenerTareaPorIdTest_WhenNotFound() {
        given(tareaRepository.findById(any(Long.class))).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tareasCRUDService.obtenerTareaPorId(999L));
    }

    @Test
    void eliminarTareaTest() {
        Long id = 1L;

        tareasCRUDService.eliminarTarea(id);

        verify(tareaRepository).deleteById(id);
    }

    @Test
    void actualizarTareaTest_WhenExists() {
        Tarea tareaExistente = Tarea.builder().id(1L).descripcion("Tarea Original").build();
        TareaDTO tareaDTOActualizada = TareaDTO.builder().id(1L).descripcion("Tarea Actualizada").build();
        Tarea tareaActualizada = Tarea.builder().id(1L).descripcion("Tarea Actualizada").build();

        given(tareaRepository.save(any(Tarea.class))).willReturn(tareaActualizada);

        TareaDTO resultado = tareasCRUDService.actualizarTarea(tareaDTOActualizada);

        assertNotNull(resultado);
        assertEquals(tareaDTOActualizada.getDescripcion(), resultado.getDescripcion());
        verify(tareaRepository).save(any(Tarea.class));
    }

    @Test
    void obtenerTodasLasTareasTest() {
        Set<Tarea> tareas = new HashSet<>();
        tareas.add(Tarea.builder().id(1L).descripcion("Tarea 1").build());
        tareas.add(Tarea.builder().id(2L).descripcion("Tarea 2").build());

        given(tareaRepository.findAll()).willReturn(new ArrayList<>(tareas));

        Set<TareaDTO> resultado = tareasCRUDService.obtenerTodasLasTareas();

        assertNotNull(resultado);
        assertEquals(tareas.size(), resultado.size());
    }
}
