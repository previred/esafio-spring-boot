package com.nuevospa.taskcontrol.controllers;

import com.nuevospa.taskcontrol.dtos.requests.AddTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.DeleteTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.UpdateTareaRequest;
import com.nuevospa.taskcontrol.dtos.responses.TareaResponse;
import com.nuevospa.taskcontrol.services.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task-control")
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @Operation(summary = "Agregar una tarea para un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea agregada")})
    @PostMapping("/add-task")
    @ResponseBody
    public ResponseEntity<TareaResponse> addTask(@RequestBody AddTareaRequest request) {
        return ResponseEntity.ok(this.tareaService.addTarea(request));
    }

    @Operation(summary = "Eliminar tarea para un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada")})
    @PostMapping("/delete-task")
    @ResponseBody
    public ResponseEntity<TareaResponse> deleteTask(@RequestBody DeleteTareaRequest request) {
        return ResponseEntity.ok(tareaService.deleteTarea(request));
    }

    @Operation(summary = "Obtener una tarea para un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea obtenida")})
    @GetMapping("/read-task/{idTarea}")
    @ResponseBody
    public ResponseEntity<TareaResponse> readTask(@PathVariable long idTarea) {
        return ResponseEntity.ok(tareaService.readTarea(idTarea));
    }

    @Operation(summary = "Actualizar una tarea para un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada")})
    @PostMapping("/update-task")
    @ResponseBody
    public ResponseEntity<TareaResponse> updateTask(@RequestBody UpdateTareaRequest request) {
        return ResponseEntity.ok(tareaService.updateTarea(request));
    }
}
