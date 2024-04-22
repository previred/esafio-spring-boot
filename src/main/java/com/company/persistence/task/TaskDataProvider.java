package com.company.persistence.task;

import com.company.exception.AppException;
import com.company.exception.enums.CodeExceptions;
import com.company.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class TaskDataProvider {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Transactional
    public void create(Task task) {
        taskRepository.save(taskMapper.toEntity(task));
    }

    @Transactional(readOnly = true)
    public Optional<Task> findById(UUID uuid)  {
        return taskRepository.findById(uuid)
                .map(taskMapper::toModel);

    }

    @Transactional(readOnly = true)
    public List<Task> getAll()  {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(UUID uuid) {
        taskRepository.deleteById(uuid);
    }




}
