package cl.nuevospa.config.exceptions.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private Integer code;
    private String message;
    
}
