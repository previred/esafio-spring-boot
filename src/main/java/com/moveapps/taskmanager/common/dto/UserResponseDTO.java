package com.moveapps.taskmanager.common.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(String user, String token) {

}
