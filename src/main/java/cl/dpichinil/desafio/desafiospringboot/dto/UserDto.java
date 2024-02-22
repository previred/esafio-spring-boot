package cl.dpichinil.desafio.desafiospringboot.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String username;
    private String password;
    private Date lastPasswordChange;
    private Date createdDate;
    private Date lastAccess;
    private boolean active;
    private boolean expired;
    private String authoritiesText;
}
