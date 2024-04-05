package co.moveapps.spa.core.controller.api;


import co.moveapps.spa.core.controller.model.TaskRequest;
import co.moveapps.spa.core.controller.model.TaskResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.service.task.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-04T21:17:31.347645-05:00[America/Bogota]")
@Controller
@RequestMapping("${openapi.swaggerCoreNuevoSPAOpenAPI30.base-path:/api}")
public class TaskApiController implements TaskApi {

    private final NativeWebRequest request;
    private final TaskServiceImpl taskService;

    @Autowired
    public TaskApiController(NativeWebRequest request, TaskServiceImpl taskService) {
        this.request = request;
        this.taskService = taskService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks(Integer page, Integer limit) {
        return ResponseEntity.ok(taskService.getAll(page, limit));
    }

    @Override
    public ResponseEntity<TaskResponse> getTaskById(UUID id) throws BusinessException {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @Override
    public ResponseEntity<Boolean> deleteTask(UUID id) throws BusinessException {
        return ResponseEntity.ok(taskService.delete(id));
    }

    @Override
    public ResponseEntity<TaskResponse> createTask(TaskRequest taskRequest) throws BusinessException {
        return ResponseEntity.ok(taskService.create(taskRequest));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(UUID id, TaskRequest taskRequest) throws BusinessException {
        return ResponseEntity.ok(taskService.update(id, taskRequest));
    }
}
