package jlillor.ms.tasks.manager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase Message.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    /** El código. */
    private String code;
    /** El mensaje. */
    private String message;

}
