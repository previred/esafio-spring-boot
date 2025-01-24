package cl.previred.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter      
    private String username;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter      
    private String email;

    @JsonIgnore // Excluye este campo de la serialización JSON
    @Column(nullable = false)
    @Getter
    @Setter      
    private String password;
		
}
