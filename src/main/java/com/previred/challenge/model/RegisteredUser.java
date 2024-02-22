package com.previred.challenge.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "registered_user")
public class RegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    private String email;

    private String password;

}
