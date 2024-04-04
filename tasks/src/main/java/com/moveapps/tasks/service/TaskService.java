package com.moveapps.tasks.service;

import com.moveapps.tasks.exceptions.CustomHandlerException;
import com.moveapps.tasks.model.Task;
import com.moveapps.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task){
        return Optional.of(taskRepository.save(task)).orElseThrow(()->
                new CustomHandlerException("Error al guardar la tarea"));
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(
                ()-> new CustomHandlerException("No se encontró la tarea con el id: " + id)
        );
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(taskList::add);
        if (taskList.isEmpty()) {
            throw new CustomHandlerException("No hay datos para mostrar");
        }
        return taskList;
    }

    public boolean deleteTask(Long id) {
        if(taskRepository.existsById(id)){
            throw new CustomHandlerException("Tarea con el ID: " + id + " no encontrado");
        }
        taskRepository.deleteById(id);
        return true;
    }

    public Task updateTask(Task task){
        return taskRepository.findById(task.getId())
                .map(finded -> {
                    copyPropsOfTask(finded, task);
                    return taskRepository.save(finded);
                })
                .orElseThrow(() -> new CustomHandlerException("Tarea con el id: " +  task.getId() + " no encontrada"));
    }

    private void copyPropsOfTask(Task exist, Task update){
        exist.setDescription(update.getDescription());
        exist.setDueDate(update.getDueDate());
        exist.setStatus(update.getStatus());
        exist.setTitle(update.getTitle());
    }
}
