package dev.rhc.apiuser.util;

import dev.rhc.apiuser.dto.TaskDto;
import dev.rhc.apiuser.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class DtoConverter {
    private final ModelMapper modelMapper;

    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<TaskDto> convertTasksToDto(List<Task> tasks){
        if (tasks == null){
            return Collections.emptyList();
        }
        List<TaskDto> list = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = convertTaskToDto(task);
            list.add(taskDto);
        }
        return list;
    }

    public TaskDto convertTaskToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setCreated(task.getCreated());
        taskDto.setModified(task.getModified());
        taskDto.setTaskState(task.getTaskState());
        return taskDto;
    }

    public Task convertDtoToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCreated(taskDto.getCreated() != null ? taskDto.getCreated() : LocalDateTime.now());
        task.setModified(LocalDateTime.now());
        task.setTaskState(taskDto.getTaskState());
        return task;
    }
}