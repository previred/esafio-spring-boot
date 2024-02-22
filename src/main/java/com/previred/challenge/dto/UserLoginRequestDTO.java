package com.previred.challenge.dto;

import javax.validation.constraints.NotEmpty;

public record UserLoginRequestDTO(
        @NotEmpty String email,
        @NotEmpty String password) {

}
