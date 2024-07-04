package com.example.demo.model.entity;


//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Task> taskList = new ArrayList<>();

//    @Column(name = "created_date")
//    private Date createdDate;
//    @Column(name = "modified_date")
//    private Date modifiedDate;
//    @Column(name= "last_login_Date")
//    private Date lastLoginDate;
//    private String token;
//    @Column(name = "is_active")
//    private boolean isActive;


}
