package cl.corellana.taskmanager.api;

import cl.corellana.taskmanager.api.model.CreateTaskRequest;
import cl.corellana.taskmanager.api.model.TaskDto;
import cl.corellana.taskmanager.service.CreateTaskService;
import cl.corellana.taskmanager.service.DeleteTaskService;
import cl.corellana.taskmanager.service.GetTaskService;
import cl.corellana.taskmanager.service.UpdateTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/task")
@Validated
@RequiredArgsConstructor
public class TaskController {

    private final DeleteTaskService deleteTaskService;
    private final GetTaskService getTaskService;
    private final CreateTaskService createTaskService;
    private final UpdateTaskService updateTaskService;

    /**
     * DELETE /task/{id} : Delete Task
     *
     * @param id (required)
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "deleteTaskId",
            summary = "Delete Task",
            tags = {},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            },
            security = {
                    @SecurityRequirement(name = "token")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    ResponseEntity<Void> deleteTaskId(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        deleteTaskService.delete(id);
        return ResponseEntity.ok(null);
    }


    /**
     * GET /task : Get Tasks
     *
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "getTask",
            summary = "Get Tasks",
            tags = {},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "token")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    ResponseEntity<Object> getTask(

    ) {
        return ResponseEntity.ok(getTaskService.getAllTasks());
    }


    /**
     * GET /task/{id} : Get Task
     *
     * @param id (required)
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "getTaskId",
            summary = "Get Task",
            tags = {},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "token")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<TaskDto> getTaskId(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(getTaskService.getById(id));
    }


    /**
     * POST /task : Create Task
     *
     * @param createTaskRequest (optional)
     * @return Created (status code 201)
     */
    @Operation(
            operationId = "postTask",
            summary = "Create Task",
            tags = {},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created")
            },
            security = {
                    @SecurityRequirement(name = "token")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {"application/json"}
    )
    ResponseEntity<Void> postTask(
            @Parameter(name = "PostTaskRequest", description = "") @Valid @RequestBody(required = false) CreateTaskRequest createTaskRequest
    ) {
        createTaskService.create(createTaskRequest);
        return ResponseEntity.ok(null);
    }


    /**
     * PUT /task/{id} : Update Task
     *
     * @param task (optional)
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "putTaskId",
            summary = "Update Task",
            tags = {},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            },
            security = {
                    @SecurityRequirement(name = "token")
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = {"application/json"}
    )
    ResponseEntity<Void> putTaskId(
            @Parameter(name = "task", description = "") @Valid @RequestBody(required = false) TaskDto task
    ) {
        updateTaskService.update(task);
        return ResponseEntity.ok(null);
    }
}
