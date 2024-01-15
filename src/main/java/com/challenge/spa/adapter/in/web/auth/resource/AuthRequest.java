package com.challenge.spa.adapter.in.web.auth.resource;

import com.challenge.spa.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record AuthRequest(
        @Schema(description = "Username for user",
                example = "user1")
        @NotNull
        String username,
        @Schema(description = "Password for user",
                example = "user1")
        @NotNull
        String password
) {
  public User toUserDomain() {
    var domain = new User();
    domain.setUsername(username);
    domain.setPassword(password);
    return domain;
  }
}
