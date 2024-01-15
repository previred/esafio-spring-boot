package com.nuevoapp.prueba.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "passwords")
public class PasswordEntity {

    @Id
    @Column(name = "user_email")
    private String userEmail;

    private String password;
}
