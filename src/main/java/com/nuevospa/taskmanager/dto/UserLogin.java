package com.nuevospa.taskmanager.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    private UUID loginId;
    private String email;
    private String password;

}
