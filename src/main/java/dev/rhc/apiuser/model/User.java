package dev.rhc.apiuser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isActive;
}
