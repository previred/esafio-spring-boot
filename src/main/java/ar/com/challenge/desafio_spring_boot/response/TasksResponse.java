package ar.com.challenge.desafio_spring_boot.response;

import ar.com.challenge.desafio_spring_boot.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksResponse {
    List<TaskDto> tasks;
}
