package jlillor.ms.tasks.manager.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * La clase TaskResponse.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {

    /** El id de la tarea. */
    private Long idTask;
    /** El título. */
    private String title;
    /** La descripción. */
    private String description;
    /** La fecha de inicio. */
    private LocalDateTime startDate;
    /** La fecha de finalización. */
    private LocalDateTime endDate;
    /** El estado de la tarea. */
    private String status;
    /** El usuario de la tarea. */
    private String user;

}
