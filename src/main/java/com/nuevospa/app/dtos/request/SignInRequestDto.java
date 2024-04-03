package com.nuevospa.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must have a valid format")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}