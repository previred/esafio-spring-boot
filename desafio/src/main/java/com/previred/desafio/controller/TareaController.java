package com.previred.desafio.controller;

import com.previred.desafio.entity.Tarea;
import com.previred.desafio.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
@Tag(name = "Gestión de Tareas", description = "Operaciones relacionadas con tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Operation(summary = "Listar todas las tareas", description = "Obtiene una lista de todas las tareas disponibles")
    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.listarTodas();
    }

    @Operation(summary = "Crear una nueva tarea", description = "Crea una tarea nueva a partir de los datos proporcionados")
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    @Operation(summary = "Actualizar una tarea existente", description = "Actualiza una tarea existente basada en el ID proporcionado")
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    @Operation(summary = "Eliminar una tarea", description = "Elimina la tarea identificada por el ID proporcionado")
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}
