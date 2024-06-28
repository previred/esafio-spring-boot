package com.previred.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    @NotBlank(message = "User is mandatory")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
