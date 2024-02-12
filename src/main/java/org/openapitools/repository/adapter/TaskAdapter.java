package org.openapitools.repository.adapter;

import org.openapitools.dto.Task;
import org.openapitools.exceptions.NotFoundException;
import org.openapitools.exceptions.enums.ExceptionEnum;
import org.openapitools.repository.TaskRepository;
import org.openapitools.repository.jpa.TaskEntityRepository;
import org.openapitools.util.MapperUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class TaskAdapter implements TaskRepository {

    private final TaskEntityRepository taskEntityRepository;

    public TaskAdapter(TaskEntityRepository taskEntityRepository) {
        this.taskEntityRepository = taskEntityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return taskEntityRepository.findAll()
                .stream()
                .map(MapperUtil::entityToTaskDto)
                .toList();
    }

    @Override
    @Transactional
    public Task save(Task task) {
        var taskEntity = taskEntityRepository.save(MapperUtil.dtoToTaskEntity(task));
        return MapperUtil.entityToTaskDto(taskEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(UUID taskId) {
        return taskEntityRepository.findById(taskId)
                .map(MapperUtil::entityToTaskDto)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.TASK_NOT_FOUND));
    }

    @Override
    @Transactional
    public void delete(UUID taskId) {
        taskEntityRepository.deleteById(taskId);
    }
}
