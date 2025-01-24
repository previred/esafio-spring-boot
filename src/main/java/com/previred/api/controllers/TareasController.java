package com.previred.api.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.previred.api.dtos.TareaDTO;
import com.previred.api.dtos.TareaResponseDTO;
import com.previred.api.services.TareasApi;
import com.previred.api.services.TareasService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TareasController implements TareasApi {

    private final TareasService tareasService;

    @Override
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody TareaDTO tareaIn) {
        TareaDTO tarea = tareasService.crearTarea(tareaIn);
        return ResponseEntity.ok(tarea);
    }

    @Override
    public ResponseEntity<TareaDTO> actualizarTarea(@RequestBody TareaDTO tareaIn) {
        TareaDTO tarea = tareasService.actualizarTarea(tareaIn);
        if (tarea != null) {
            return ResponseEntity.ok(tarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long tareaId) {
        return tareasService.eliminarTarea(tareaId);
    }

    @Override
    public ResponseEntity<TareaResponseDTO> buscarPorId(@PathVariable Long tareaId) {
        TareaResponseDTO tarea = tareasService.buscarPorId(tareaId);
        if (tarea != null) {
            return ResponseEntity.ok(tarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<TareaResponseDTO>> listarTareas() {
        List<TareaResponseDTO> listaTareas = tareasService.listarTareas();
        if (!listaTareas.isEmpty()) {
            return ResponseEntity.ok(listaTareas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
