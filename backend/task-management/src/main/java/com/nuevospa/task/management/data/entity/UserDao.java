package com.nuevospa.task.management.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="usuarios")
public class UserDao {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @Column(name="name", length = 50)
    private String name;
    

    @Column(name="email", nullable = false, length = 50, unique = true)
    private String email;
    
    
    @Column(name="password", nullable = false, length = 100)
    private String password;
    

    @Column(name="status", nullable = false)
    private boolean status;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    

    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskDao> task;

}
