package com.desafio.task.mappers;

import com.desafio.task.dto.TaskDTO;
import com.desafio.task.entity.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskMapper {

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    public Task convertToEntity(TaskDTO dto){
        return modelMapper.map(dto, Task.class);
    }

    public TaskDTO convertToDto(Task obj){
        return modelMapper.map(obj, TaskDTO.class);
    }
}
