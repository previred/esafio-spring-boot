package ar.com.challenge.desafio_spring_boot.request;

import ar.com.challenge.desafio_spring_boot.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    TaskDto taskDto;
}
