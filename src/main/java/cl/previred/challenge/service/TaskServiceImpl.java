package cl.previred.challenge.service;

import cl.previred.challenge.config.security.JwtUserData;
import cl.previred.challenge.dto.TaskDto;
import cl.previred.challenge.dto.request.AssignTaskToRequest;
import cl.previred.challenge.dto.request.ChangeTaskStatusRequest;
import cl.previred.challenge.dto.request.NewTaskRequest;
import cl.previred.challenge.repository.TaskRepository;
import cl.previred.challenge.repository.TaskStatusRepository;
import cl.previred.challenge.repository.UserRepository;
import cl.previred.challenge.repository.entity.Task;
import cl.previred.challenge.repository.entity.TaskStatus;
import cl.previred.challenge.repository.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static cl.previred.challenge.dto.TaskDto.toDto;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskStatusRepository taskStatusRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskDto> taskList() {
        return taskRepository.findAll().stream().map(TaskDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> taskListByUser(Integer id) {
        return taskRepository.findByCreatedBy_Id(id).stream().map(TaskDto::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> createTask(NewTaskRequest newTaskRequest, JwtUserData userData) {

        Optional<TaskStatus> optStatus = taskStatusRepository.findByCode(TaskStatusEnum.CREATED.name());

        if (optStatus.isEmpty())
            throw new IllegalArgumentException("No se encontro estado en bd.");

        Optional<User> optAssignedTo = Objects.nonNull(newTaskRequest.assignedToEmail())?
                userRepository.findByEmail(newTaskRequest.assignedToEmail())
                : Optional.empty();
        User assignedTo = optAssignedTo.orElse(null);

        return userRepository.findByEmail(userData.username())
                .map(user -> toDto(taskRepository.save(buildTask(newTaskRequest, optStatus.get(), user, assignedTo))));
    }

    private Task buildTask(NewTaskRequest newTaskRequest, TaskStatus taskStatus, User createdBy, User assignedTo) {

        return new Task(
                    newTaskRequest.name(),
                    newTaskRequest.description(),
                    LocalDateTime.now(),
                    createdBy,
                    assignedTo,
                    taskStatus);
    }

    @Override
    public Optional<Void> deleteTask(Integer taskId, JwtUserData userData) {
        taskRepository.deleteById(taskId);
        return Optional.empty();
    }

    @Override
    public Optional<TaskDto> updateTaskContent(NewTaskRequest newTaskRequest, Integer taskId, JwtUserData userData) {

        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (!task.getCreatedBy().getEmail().equals(userData.username()))
                throw new TaskReadOnlyException();
            return Optional.of(toDto(taskRepository.save(patchTaskContent(task, newTaskRequest))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TaskDto> assignTaskTo(AssignTaskToRequest assignTaskToRequest, Integer taskId) {

        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<User> optionalUser = userRepository.findByEmail(assignTaskToRequest.userEmail());

        if (optionalTask.isPresent() && optionalUser.isPresent()) {
            return Optional.of(toDto(taskRepository.save(patchAssigned(optionalTask.get(), optionalUser.get()))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TaskDto> changeTaskStatus(ChangeTaskStatusRequest taskStatusRequest, Integer taskId) {

        Optional<TaskStatus> optStatus = taskStatusRepository.findByCode(taskStatusRequest.status());

        if (optStatus.isEmpty())
            throw new IllegalArgumentException("Estado tarea no existe");

        return taskRepository.findById(taskId).map(task -> toDto(taskRepository.save(patchTaskStatus(task, optStatus.get()))));
    }

    private Task patchTaskContent(Task task, NewTaskRequest newTaskRequest) {
        task.setName(newTaskRequest.name());
        task.setDescription(newTaskRequest.description());
        return task;
    }

    private Task patchTaskStatus(Task task, TaskStatus taskStatus) {
        task.setStatus(taskStatus);
        return task;
    }

    private Task patchAssigned(Task task, User assigned) {
        task.setAssignedTo(assigned);
        return task;
    }
}
