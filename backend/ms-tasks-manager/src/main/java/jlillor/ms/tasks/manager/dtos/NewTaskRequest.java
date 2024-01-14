package jlillor.ms.tasks.manager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * La clase NewTaskRequest.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTaskRequest {

	/** El título. */
	private String title;
	/** La descripción. */
	private String description;
	/** La fecha de inicio. */
	private LocalDateTime startDate;
	/** El estado de la tarea. */
	private String status;
	/** El usuario de la tarea. */
	private String userIdentifier;

}
