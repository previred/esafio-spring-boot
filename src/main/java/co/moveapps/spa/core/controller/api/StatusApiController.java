package co.moveapps.spa.core.controller.api;


import co.moveapps.spa.core.controller.model.StatusResponse;
import co.moveapps.spa.core.service.task.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-05T04:32:44.594249-05:00[America/Bogota]")
@Controller
@RequestMapping("${openapi.swaggerCoreNuevoSPAOpenAPI30.base-path:/api}")
public class StatusApiController implements StatusApi {

    private final NativeWebRequest request;
    private final TaskServiceImpl taskService;

    @Autowired
    public StatusApiController(NativeWebRequest request, TaskServiceImpl taskService) {
        this.request = request;
        this.taskService = taskService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<StatusResponse>> getAllStatusTasks(Integer page, Integer limit) {
        return ResponseEntity.ok(taskService.getAllStates(page, limit));
    }
}
