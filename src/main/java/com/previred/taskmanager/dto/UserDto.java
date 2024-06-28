package com.previred.taskmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotBlank(message = "Role is mandatory")
    private String role;
}
