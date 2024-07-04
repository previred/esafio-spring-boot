package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
@Table(name = "Task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    private String statusCode;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "taskStatusid")
    @NotNull
    private TaskStatus statusCode;


    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "userEntityid")
    @NotNull
    private UserEntity userEntity;

//    @ManyToOne(fetch = FetchType.EAGER,optional = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JoinColumn(name = "task_status_id")
//    @NotNull
//    private TaskStatus estado;

}
