package com.nuevospa.taskmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login", schema = "public")
public class LoginEntity {

    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID loginId;
    private String email;
    private String password;
}
