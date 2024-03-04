package cl.previred.desafio.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.desafio.dto.TareasDto;
import cl.previred.desafio.model.Tareas;
import cl.previred.desafio.service.TareasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tareas Controller", description = "Endpoints para Tareas")
@RestController
@RequestMapping("/tasks")
public class TareasController {

    @Autowired
    private TareasService tasksService;

    @Operation(summary = "Crear nueva tarea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
                    example = "[{\"nameTask\":\"NombreTarea1\",\"statusTask\":{\"status\":\"VALID o INVALID\"}},{\"nameTask\":\"NombreTarea2\",\"statusTask\":{\"status\":\"VALID o INVALID\"}}]"))))
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addTasks")
    public @ResponseBody String saveTasks(@RequestBody List<Tareas> tasks) {
        String message = "";
        try {
            message = tasksService.saveTask(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            message = "No se guardo correctamente la(s) Tarea(s), error: " + e.getMessage();
        }

        return message;
    }

    @Operation(summary = "Actualizar tarea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
                    example = "{\"id\":0,\"nameTask\":\"Tarea1\",\"statusTask\":{\"status\":\"VALID o INVALID\"}}"))))
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/updateTask")
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
            message = "No se actualizo correctamente la(s) Tarea(s), error: " + e.getMessage();
        }

        return message;
    }

    @Operation(summary = "Buscar todas las tareas")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/findAllTasks")
    public @ResponseBody List<TareasDto> getAllTasks(){
        List<TareasDto> list = null;
        try {
            list = tasksService.getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Operation(summary = "Buscar tarea por id de tarea")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/findAllTasks/{id}")
    public @ResponseBody TareasDto getTaskById(@Parameter(description = "Id de tarea", required = true) @PathVariable(value="id") int id) throws Exception{
        TareasDto task = null;
        try {
            task = tasksService.findTaskById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    @Operation(summary = "Borrar tarea por id de tarea")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/deleteTasks/{id}")
    public @ResponseBody String deleteTaskById(@Parameter(description = "Id de tarea", required = true) @PathVariable(value="id") int id) {
        String message = null;
        try {
            tasksService.deleteTaskById(id);
            message = "Se ha eliminado la tarea con id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
            message = "No se ha eliminado la tarea con id: " + id;
        }
        return message;
    }
}
