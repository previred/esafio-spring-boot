package com.nuevospa.taskmanager.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID userId;
    private String identificationNumber;
    private String name;
    private String phone;
    private String email;
    private String password;

}
