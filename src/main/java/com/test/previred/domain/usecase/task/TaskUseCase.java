package com.test.previred.domain.usecase.task;

import com.test.previred.domain.model.task.Task;
import com.test.previred.domain.model.task.gateway.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskUseCase {

    private final TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }


    public Task findById(String id) {
        return taskRepository.findById(id);
    }

    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }

    public Task update(Task task) {
        return taskRepository.update(task);
    }

}
