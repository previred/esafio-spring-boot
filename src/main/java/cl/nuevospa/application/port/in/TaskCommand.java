package cl.nuevospa.application.port.in;

import java.util.List;

import cl.nuevospa.domain.api.Task;

public interface TaskCommand {
    Task save(Task task);
    void delete(Integer id);
    Task update(Integer id, Task task);
    Task get(Integer id);
    List<Task> getAll();
    
}
