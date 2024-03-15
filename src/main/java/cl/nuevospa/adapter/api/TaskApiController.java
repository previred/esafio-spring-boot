package cl.nuevospa.adapter.api;

import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import cl.nuevospa.application.port.in.TaskCommand;
import cl.nuevospa.domain.api.Task;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-15T17:58:12.455469200-03:00[America/Santiago]")
@Controller
public class TaskApiController implements TaskApi {
    private final TaskCommand taskCommand;
    private final NativeWebRequest request;

    @Autowired
    public TaskApiController(NativeWebRequest request, TaskCommand taskCommand) {
        this.request = request;
        this.taskCommand=taskCommand;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    @Override
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task
            ) {
        
        return new ResponseEntity<>(taskCommand.save(task),HttpStatus.CREATED);
        
    }
    @Override
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id
            ) 
             {
        taskCommand.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    @Override
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @Valid @RequestBody Task task)
             {
        
        return new ResponseEntity<>(taskCommand.update(id,task),HttpStatus.OK);
        
    }
    
    @Override
    public ResponseEntity<Task> getTask(@PathVariable("id") Integer id)
             {
        
        return new ResponseEntity<>(taskCommand.get(id),HttpStatus.OK);
        
    }
    @Override
    public ResponseEntity<List<Task>> getTasks(){
        return new ResponseEntity<>(taskCommand.getAll(),HttpStatus.OK);
    }
    
}