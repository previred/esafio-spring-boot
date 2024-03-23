package com.reto.tecnico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taskId;
  private String title;
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  @JsonIgnore
  private User user;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) // Cambia FetchType.LAZY a FetchType.EAGER
  private List<TaskStatus> lstTaskStatus = new ArrayList<>();
}
