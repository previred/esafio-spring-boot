package cl.previred.desafio.service.impl;

import cl.previred.desafio.entity.TaskEntity;
import cl.previred.desafio.repository.TaskRepository;
import cl.previred.desafio.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskEntity> findAll() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    @Override
    public TaskEntity update(UUID id, TaskEntity newTask) {
        TaskEntity update = this.findById(id);
        update.setDescription(newTask.getDescription());
        update.setStatus(newTask.getStatus());
        return taskRepository.save(update);
    }

    @Override
    public TaskEntity delete(UUID id) {
        TaskEntity del = this.findById(id);
        taskRepository.delete(del);
        return del;
    }
}
