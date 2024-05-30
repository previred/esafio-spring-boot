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
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "identification_number", nullable = false)
    private String identificationNumber;
    private String name;
    private String phone;
    @JoinColumn(name = "login_id")
    @OneToOne(fetch = FetchType.LAZY)
    private LoginEntity login;
}
