package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.service.TareasCRUDService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TareasCRUDControllerTest {
    @Mock
    private TareasCRUDService tareasCRUDService;
    @InjectMocks
    private TareasCRUDController tareasCRUDController;

    @Test
    void crearTarea_DebeRetornarTareaCreada() {
        TareaDTO tareaDTO = new TareaDTO();
        TareaDTO tareaCreadaDTO = new TareaDTO();

        when(tareasCRUDService.crearTarea(any(TareaDTO.class))).thenReturn(tareaCreadaDTO);

        ResponseEntity<TareaDTO> response = tareasCRUDController.crearTarea(tareaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void actualizarTarea_DebeRetornarTareaActualizada() {
        TareaDTO tareaDTO = new TareaDTO();
        when(tareasCRUDService.actualizarTarea(any(TareaDTO.class))).thenReturn(tareaDTO);

        ResponseEntity<TareaDTO> response = tareasCRUDController.actualizarTarea(1L, tareaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void eliminarTarea_DebeRetornarNoContent() {
        doNothing().when(tareasCRUDService).eliminarTarea(anyLong());

        ResponseEntity<Void> response = tareasCRUDController.eliminarTarea(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void obtenerTareaPorId_DebeRetornarTarea() {
        TareaDTO tareaDTO = new TareaDTO();
        when(tareasCRUDService.obtenerTareaPorId(anyLong())).thenReturn(tareaDTO);

        ResponseEntity<TareaDTO> response = tareasCRUDController.obtenerTareaPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void obtenerTodasLasTareas_DebeRetornarConjuntoTareas() {
        Set<TareaDTO> tareaDTOSet = new HashSet<>();
        when(tareasCRUDService.obtenerTodasLasTareas()).thenReturn(tareaDTOSet);

        ResponseEntity<Set<TareaDTO>> response = tareasCRUDController.obtenerTodasLasTareas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
