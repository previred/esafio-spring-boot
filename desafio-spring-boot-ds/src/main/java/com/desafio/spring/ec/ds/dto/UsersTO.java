package com.desafio.spring.ec.ds.dto;

import com.desafio.spring.ec.ds.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersTO {

    private String id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private List<Role> roles;
}
