package co.com.task.api.dto;

import java.io.Serializable;

import co.com.task.api.utility.Utilities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    private String email;
    private String pass;

    @Override
    public String toString() {
	return Utilities.toStringObjec(this);
    }

}
