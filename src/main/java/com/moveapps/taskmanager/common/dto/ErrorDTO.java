package com.moveapps.taskmanager.common.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(String message, String code) {

}
