package nuevo.spa.app.expose.web;

import com.spring.nuevo.spa.app.model.TaskListResponse;
import com.spring.nuevo.spa.app.model.TaskRequest;
import com.spring.nuevo.spa.app.model.TaskResponse;
import lombok.RequiredArgsConstructor;
import nuevo.spa.app.business.SpaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/spa", produces = "application/json")
public class SpaController {

    private final SpaService service;

    @GetMapping(value = "/task")
    public ResponseEntity<List<TaskListResponse>> getTask() {
        return new ResponseEntity<>(service.getTask(), HttpStatus.OK);
    }

    @PostMapping(value = "/task")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskRequest request) {
        return new ResponseEntity<>(service.createTask(request), HttpStatus.OK);
    }

    @PutMapping(value = "/task/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") String id, @RequestBody @Valid TaskRequest request) {
        return new ResponseEntity<>(service.updateTask(id, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {
        service.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
