package io.swagger.api;

import io.swagger.annotations.AuthRequired;
import io.swagger.model.Task;
import io.swagger.model.TaskPage;
import io.swagger.model.TaskResponse;
import io.swagger.service.TaskService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-03T04:30:42.818554393Z[GMT]")
@Slf4j
@RestController
@RequiredArgsConstructor
public class TasksApiController implements TasksApi {

    private final TaskService taskService;

    @AuthRequired
    public ResponseEntity<TaskResponse> addTask(@Parameter(in = ParameterIn.DEFAULT, description = "Adds a new task", required=true, schema=@Schema()) @Valid @RequestBody Task body
) {
        return ResponseEntity.ok(taskService.addTask(body));
    }

    @AuthRequired
    public ResponseEntity<Void> deleteTask(@Parameter(in = ParameterIn.PATH, description = "Task id to delete", required=true, schema=@Schema()) @PathVariable("taskId") Long taskId
) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @AuthRequired
    public ResponseEntity<TaskResponse> getTaskById(@Parameter(in = ParameterIn.PATH, description = "ID of task to return", required=true, schema=@Schema()) @PathVariable("taskId") Long taskId
) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @AuthRequired
    public ResponseEntity<TaskPage> listTasks(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Current page of list" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "page", required = true) Long page
,@NotNull @Parameter(in = ParameterIn.QUERY, description = "Size of the page" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "size", required = true) Long size
,@Parameter(in = ParameterIn.QUERY, description = "Title of the task to filter" ,schema=@Schema()) @Valid @RequestParam(value = "title", required = false) String title
,@Parameter(in = ParameterIn.QUERY, description = "Description of the task to filter" ,schema=@Schema()) @Valid @RequestParam(value = "description", required = false) String description
,@Parameter(in = ParameterIn.QUERY, description = "Status of tasks to return" ,schema=@Schema()) @Valid @RequestParam(value = "status", required = false) Long status
) {
        return ResponseEntity.ok(taskService.listTasks(page, size, title, description, status));
    }

    @AuthRequired
    public ResponseEntity<TaskResponse> updateTaskWithForm(@Parameter(in = ParameterIn.PATH, description = "ID of task that needs to be updated", required=true, schema=@Schema()) @PathVariable("taskId") Long taskId
,@Parameter(in = ParameterIn.QUERY, description = "Title of task that needs to be updated" ,schema=@Schema()) @Valid @RequestParam(value = "title", required = false) String title
,@Parameter(in = ParameterIn.QUERY, description = "Description of task that needs to be updated" ,schema=@Schema()) @Valid @RequestParam(value = "description", required = false) String description
,@Parameter(in = ParameterIn.QUERY, description = "Status of task that needs to be updated" ,schema=@Schema()) @Valid @RequestParam(value = "status", required = false) Long status
) {
        return ResponseEntity.ok(taskService.updateTaskWithForm(taskId, title, description, status));
    }

    @AuthRequired
    public ResponseEntity<TaskResponse> updateTask(@Parameter(in = ParameterIn.PATH, description = "ID of task to return", required=true, schema=@Schema()) @PathVariable("taskId") Long taskId
,@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent task", required=true, schema=@Schema()) @Valid @RequestBody Task body
) {
        return ResponseEntity.ok(taskService.updateTask(taskId, body));
    }

}
