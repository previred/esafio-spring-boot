package com.previred.taskmanager.service;

import com.previred.taskmanager.dto.TaskDto;
import com.previred.taskmanager.exception.TaskManagerException;
import com.previred.taskmanager.model.Priority;
import com.previred.taskmanager.model.Status;
import com.previred.taskmanager.model.Task;
import com.previred.taskmanager.repository.PriorityRepository;
import com.previred.taskmanager.repository.StatusRepository;
import com.previred.taskmanager.repository.TaskRepository;
import com.previred.taskmanager.util.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;
    private final PriorityRepository priorityRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::taskToTaskDTO).toList();
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskMapper.taskToTaskDTO(findById(id));
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task savedTask = taskRepository.save(taskMapper.taskDTOToTask(taskDto));
        return taskMapper.taskToTaskDTO(savedTask);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        findById(taskDto.getId());
        Task updatedTask = taskMapper.taskDTOToTask(taskDto);
        return taskMapper.taskToTaskDTO(taskRepository.save(updatedTask));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto updateTaskStatus(Long id, String statusName) {
        Task task = findById(id);
        Status status = statusRepository.findByName(statusName).orElseThrow(() -> new TaskManagerException("Status not found: " + statusName));
        task.setStatus(status);
        return taskMapper.taskToTaskDTO(taskRepository.save(task));
    }

    @Override
    public TaskDto updatePriority(Long id, String priorityName) {
        Task task = findById(id);
        Priority priority = priorityRepository.findByName(priorityName).orElseThrow(() -> new TaskManagerException("Priority not found: " + priorityName));
        task.setPriority(priority);
        return taskMapper.taskToTaskDTO(taskRepository.save(task));
    }

    private Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskManagerException("Task not found: " + id));
    }
}
