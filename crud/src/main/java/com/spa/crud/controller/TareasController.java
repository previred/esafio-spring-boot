package com.spa.crud.controller;

import com.spa.crud.dto.TareasDTO;
import com.spa.crud.model.Tareas;
import com.spa.crud.service.TareasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tareas Controller", description = "Endpoints para Tareas")
@RestController
@RequestMapping("/tasks")
public class TareasController {
    @Autowired
    private TareasService tasksService;

    @Operation(summary = "Crear nueva tarea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
                    example = "[{\"NombreTarea\":\"NombreTarea1\",\"estadosTarea\":{\"idEstado\":\"1\", \"nombreEstado\":\"SIN EMPEZAR\"}},{\"NombreTarea\":\"NombreTarea2\",\"estadosTarea\":{\"idEstado\":\"1\", \"nombreEstado\":\"SIN EMPEZAR\"}}]"))))
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/crearTarea")
    public @ResponseBody String saveTasks(@RequestBody Tareas task) {
        String message = "";
        try {
            message = tasksService.saveTask(task);
        } catch (Exception e) {
            e.printStackTrace();
            message = "No se puedo guardar la tarea, Error: " + e.getMessage();
        }

        return message;
    }

    @Operation(summary = "Actualizar tarea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
                    example = "{\"id\":1,\"nombreTarea\":\"Tarea1\",\"numeroTarea\":\"1004\",\"EstadosTarea\":{\"idEstado\":\"2\", \"nombreEstado\":\"EN EJECUCION\"}}"))))
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/actualizarTarea")
    public @ResponseBody String updateTasks(@RequestBody Tareas task) {
        String message = "";
        try {
            if(task.getId() != 0) {
                message = tasksService.updateTask(task);
            }
            else {
                message = "Para actualizar debe venir el id de la tarea";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "No se actualizo correctamente la Tarea, Error: " + e.getMessage();
        }

        return message;
    }

    @Operation(summary = "Buscar todas las tareas")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/getAll")
    public @ResponseBody List<TareasDTO> getAllTasks(){
        List<TareasDTO> list = null;
        try {
            list = tasksService.getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Operation(summary = "Buscar tarea por id de tarea")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/findTarea/{id}")
    public @ResponseBody TareasDTO getTaskById(@Parameter(description = "Id de tarea", required = true) @PathVariable(value="id") Long id) throws Exception{
        TareasDTO task = null;
        try {
            task = tasksService.findTaskById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    @Operation(summary = "Borrar tarea por numero de tarea")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/deleteTasks/{numeroTarea}")
    public @ResponseBody String deleteTaskById(@Parameter(description = "numero de Tarea", required = true) @PathVariable(value="numeroTarea") String numeroTarea) {
        String message = null;
        try {
            tasksService.deleteTaskByNumeroTarea(numeroTarea);
            message = "Se ha eliminado la tarea con numeroTarea: " + numeroTarea;
        } catch (Exception e) {
            e.printStackTrace();
            message = "No se ha eliminado la tarea: " + numeroTarea;
        }
        return message;
    }
}
