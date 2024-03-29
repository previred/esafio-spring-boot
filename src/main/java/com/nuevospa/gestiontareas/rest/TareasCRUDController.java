package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.service.TareasCRUDService;
import com.nuevospa.gestiontareas.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class TareasCRUDController {
    @Autowired
    private TareasCRUDService tareasCRUDService;

    @PostMapping("/tareas")
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody TareaDTO tareaDTO) {
        TareaDTO dto = SecurityUtils.sanitize(tareaDTO);
        TareaDTO nuevaTarea = tareasCRUDService.crearTarea(dto);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    @PutMapping("/tareas/{id}")
    public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable Long id, @RequestBody TareaDTO tareaDTO) {
        TareaDTO dto = SecurityUtils.sanitize(tareaDTO);
        dto.setId(id);
        return ResponseEntity.ok(tareasCRUDService.actualizarTarea(dto));
    }

    @DeleteMapping("/tareas/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareasCRUDService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tareas/{id}")
    public ResponseEntity<TareaDTO> obtenerTareaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tareasCRUDService.obtenerTareaPorId(id));
    }

    @GetMapping("/tareas")
    public ResponseEntity<Set<TareaDTO>> obtenerTodasLasTareas() {
        return ResponseEntity.ok(tareasCRUDService.obtenerTodasLasTareas());
    }
}
