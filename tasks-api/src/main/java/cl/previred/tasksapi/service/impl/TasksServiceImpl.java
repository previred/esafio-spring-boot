package cl.previred.tasksapi.service.impl;

import cl.previred.tasksapi.exceptions.TaskException;
import cl.previred.tasksapi.mappers.TaskMapper;
import cl.previred.tasksapi.model.TaskModel;
import cl.previred.tasksapi.repository.TaskRepository;
import cl.previred.tasksapi.repository.TaskStatusRepository;
import cl.previred.tasksapi.service.TasksService;
import cl.previred.tasksapi.util.ConstantsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Task;
import org.openapitools.model.TaskInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@Slf4j
public class TasksServiceImpl implements TasksService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;


    @Override
    public List<Task> getAllTasks() {
        log.info("getAllTasks");

        return Optional.of(
                        taskRepository.findAll()
                )
                .map(TaskMapper.INSTANCE::entityListToDTOList)
                .orElse(new ArrayList<>());
    }

    @Transactional
    @Override
    public Task updateTask(TaskInput input, int taskId) {
        var updateTaskModel = taskRepository.findById(taskId).orElseThrow(() -> new TaskException(String.format(ConstantsUtil.ERROR_MESSAGE_NO_TASK_ID_FOUND, taskId)));
        var taskStatus = taskStatusRepository.findById(input.getTaskStatusId()).orElseThrow(() -> new TaskException(String.format(ConstantsUtil.ERROR_MESSAGE_NO_TASK_STATUS_FOUND, input.getTaskStatusId())));

        updateTaskModel.setTaskName(input.getTaskName());
        input.getTaskDescription().ifPresent(updateTaskModel::setTaskDescription);
        updateTaskModel.setTaskStatus(taskStatus);

        AtomicReference<Task> dto = new AtomicReference<>();
        Optional.of(updateTaskModel)
                .ifPresent(etty -> dto.set(TaskMapper.INSTANCE.entityToDTO(taskRepository.saveAndFlush(etty))));
        return dto.get();
    }

    @Transactional
    @Override
    public Task newTask(TaskInput input) {
        var taskStatus = taskStatusRepository.findById(input.getTaskStatusId()).orElseThrow(() -> new TaskException(String.format(ConstantsUtil.ERROR_MESSAGE_NO_TASK_STATUS_FOUND, input.getTaskStatusId())));

        AtomicReference<Task> dto = new AtomicReference<>();
        TaskModel newTask = TaskModel.builder().taskName(input.getTaskName()).taskDescription(input.getTaskDescription().get()).taskStatus(taskStatus).build();
        Optional.of(newTask)
                .ifPresent(etty -> dto.set(TaskMapper.INSTANCE.entityToDTO(taskRepository.saveAndFlush(etty))));
        return dto.get();
    }

    @Override
    public Task getTask(int taskId) {
        return taskRepository.findById(taskId).map(TaskMapper.INSTANCE::entityToDTO).orElseThrow(() -> new TaskException(String.format(ConstantsUtil.ERROR_MESSAGE_NO_TASK_ID_FOUND, taskId)));
    }

    @Override
    public Task getTask(String taskName) {
        return taskRepository.findByTaskName(taskName).map(TaskMapper.INSTANCE::entityToDTO).orElseThrow(() -> new TaskException(String.format(ConstantsUtil.ERROR_MESSAGE_NO_TASK_NAME_FOUND, taskName)));
    }

    @Override
    public Boolean deleteTask(int taskId) {
        taskRepository.findById(taskId).ifPresent( d -> {
            taskRepository.deleteById(d.getTaskId());
        });
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteTask(String taskName) {
        taskRepository.findByTaskName(taskName).ifPresent( d -> {
            taskRepository.deleteByTaskName(d.getTaskName());
        });
        return Boolean.TRUE;
    }
}
