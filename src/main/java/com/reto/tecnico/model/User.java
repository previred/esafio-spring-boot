package com.reto.tecnico.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;
  private String username;
  private String password;
  private String fullName;
  private Boolean isActive;
  private LocalDateTime created;
  private LocalDateTime lastLogin;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tasks> lstTasks = new ArrayList<>();

}
