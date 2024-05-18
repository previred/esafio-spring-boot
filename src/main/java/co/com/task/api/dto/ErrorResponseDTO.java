package co.com.task.api.dto;

import co.com.task.api.utility.Utilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private int    code;
    private String message;
    private String timestamp;

    @Override
    public String toString() {
	return Utilities.toStringObjec(this);
    }

}
