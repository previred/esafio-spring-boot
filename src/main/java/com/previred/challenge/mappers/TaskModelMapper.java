package com.previred.challenge.mappers;

import com.previred.challenge.dto.TaskRequestDTO;
import com.previred.challenge.dto.TaskResponseDTO;
import com.previred.challenge.model.TaskModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskModelMapper {

    TaskModel toModel(TaskRequestDTO taskRequestDTO);

    List<TaskModel> toModelList(Iterable<TaskRequestDTO> taskRequestDTOList);

    TaskResponseDTO toResponseDTO(TaskModel taskModel);

}
