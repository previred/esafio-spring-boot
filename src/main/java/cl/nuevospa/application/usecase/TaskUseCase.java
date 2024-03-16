package cl.nuevospa.application.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nuevospa.application.port.in.TaskCommand;
import cl.nuevospa.application.port.out.StatusRepository;
import cl.nuevospa.application.port.out.TaskRepository;
import cl.nuevospa.config.exceptions.NotFoundException;
import cl.nuevospa.config.exceptions.errors.ErrorCode;
import cl.nuevospa.domain.api.Status;
import cl.nuevospa.domain.api.Task;
import cl.nuevospa.domain.h2.entities.StatusDTO;
import cl.nuevospa.domain.h2.entities.TaskDTO;

@Service
public class TaskUseCase implements TaskCommand{

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    StatusRepository statusRepository;
    @Override
    public Task save(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(task.getTitle());

        taskDTO.setDescription(task.getDescription());
        StatusDTO statusDTO = statusRepository.findByCode(task.getStatus().getCodigo()).orElseThrow(()->new NotFoundException(ErrorCode.STATUS_RESOURCE_ACCESS_ERROR));
        taskDTO.setStatus(statusDTO );
        taskRepository.save(taskDTO);
        task.setId(taskDTO.getId());
        return task;
    }
    @Override
    public void delete(Integer id) {
        TaskDTO taskDTO = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.TASK_RESOURCE_ACCESS_ERROR));
        taskRepository.delete(taskDTO);
    }
    @Override
    public Task update(Integer id, Task task) {
        TaskDTO taskDTO = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.TASK_RESOURCE_ACCESS_ERROR));
        StatusDTO statusDTO = statusRepository.findByCode(task.getStatus().getCodigo()).orElseThrow(()->new NotFoundException(ErrorCode.STATUS_RESOURCE_ACCESS_ERROR));
        taskDTO.setStatus(statusDTO);
        taskDTO.setDescription(task.getDescription());
        taskDTO.setTitle(task.getTitle());
        taskRepository.save(taskDTO);
        return task;
    }
    @Override
    public Task get(Integer id) {
        TaskDTO taskDTO = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.TASK_RESOURCE_ACCESS_ERROR));
        
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setId(taskDTO.getId());
        task.setDescription(taskDTO.getDescription());
        Status status = new Status();
        status.setCodigo(taskDTO.getStatus().getCode());
        task.setStatus(status);
        return task;
    }
    @Override
    public List<Task> getAll() {
        List<TaskDTO> tasks = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        tasks.forEach((t)->{
            Status status = new Status();
            status.setCodigo(t.getStatus().getCode());
            Task task = new Task();
            task.setDescription(t.getDescription());
            task.setId(t.getId());
            task.setTitle(t.getTitle());
            task.setStatus(status);
            taskList.add(task);
        });
        return taskList;
    }

}
