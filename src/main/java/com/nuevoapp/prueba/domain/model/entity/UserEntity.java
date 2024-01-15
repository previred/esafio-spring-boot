package com.nuevoapp.prueba.domain.model.entity;

import com.nuevoapp.prueba.domain.model.enums.UserRolesEnum;
import com.nuevoapp.prueba.domain.model.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRolesEnum role;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    private ZonedDateTime lastLoginDate;

}
