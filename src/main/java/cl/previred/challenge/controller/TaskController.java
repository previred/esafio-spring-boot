package cl.previred.challenge.controller;

import cl.previred.challenge.config.security.JwtUserData;
import cl.previred.challenge.config.security.User;
import cl.previred.challenge.controller.handler.ResponseHandler;
import cl.previred.challenge.controller.handler.ResponseWrapper;
import cl.previred.challenge.dto.TaskDto;
import cl.previred.challenge.dto.request.AssignTaskToRequest;
import cl.previred.challenge.dto.request.ChangeTaskStatusRequest;
import cl.previred.challenge.dto.request.NewTaskRequest;
import cl.previred.challenge.service.TaskServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Api(tags = "Tareas")
@ApiOperation(value = "Gestor Tareas Previred")
public class TaskController extends ResponseHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(
            value = "Obtener todas las tareas",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = TaskDto.class,
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @GetMapping("/all")
    public ResponseEntity<ResponseWrapper<List<TaskDto>>> allTasks() {
        return wrapResponse(taskService::taskList);
    }

    @ApiOperation(
            value = "Obtener todas las tareas creadas por un determinado usuario",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = TaskDto.class,
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @GetMapping("/{userId}/all")
    public ResponseEntity<ResponseWrapper<List<TaskDto>>> allTasksByUser(@PathVariable Integer userId) {
        return wrapResponse(() -> taskService.taskListByUser(userId));
    }

    @ApiOperation(
            value = "Crear nueva tarea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseWrapper<Void>> newTask(@RequestBody NewTaskRequest newTaskRequest, @User JwtUserData userData) {
        return wrapWithNoResponse(() -> taskService.createTask(newTaskRequest, userData));
    }

    @ApiOperation(
            value = "Actualizar contenido de tarea (nombre, descripcion)",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = TaskDto.class,
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @PatchMapping("/{taskId}/content")
    public ResponseEntity<ResponseWrapper<TaskDto>> updateTaskContent(@RequestBody NewTaskRequest newTaskRequest,
                                                                      @PathVariable Integer taskId, @User JwtUserData userData) {
        return wrapResponse(() -> taskService.updateTaskContent(newTaskRequest, taskId, userData).orElseThrow());
    }

    @ApiOperation(
            value = "Actualizar estado de tarea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = TaskDto.class,
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<ResponseWrapper<TaskDto>> updateTaskStatus(@RequestBody ChangeTaskStatusRequest taskStatusRequest,
                                                                     @PathVariable Integer taskId) {
        return wrapResponse(() -> taskService.changeTaskStatus(taskStatusRequest, taskId).orElseThrow());
    }

    @ApiOperation(
            value = "Asignar tarea a usuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = TaskDto.class,
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @PatchMapping("/{taskId}/assign")
    public ResponseEntity<ResponseWrapper<TaskDto>> assignTaskTo(@RequestBody AssignTaskToRequest assignTaskToRequest,
                                                                     @PathVariable Integer taskId) {
        return wrapResponse(() -> taskService.assignTaskTo(assignTaskToRequest, taskId).orElseThrow());
    }

    @ApiOperation(
            value = "Eliminar tarea",
            authorizations = {@Authorization(HttpHeaders.AUTHORIZATION)})
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper<Void>> delete(@PathVariable Integer taskId, @User JwtUserData userData) {
        return wrapWithNoResponse(() -> taskService.deleteTask(taskId, userData));
    }
}
