package co.com.task.api.dto;

import java.io.Serializable;

import co.com.task.api.utility.Utilities;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String token;
    
    @Override
    public String toString() {
    	return Utilities.toStringObjec(this);
    }

}
