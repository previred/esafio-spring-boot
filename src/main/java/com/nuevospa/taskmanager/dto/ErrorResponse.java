package com.nuevospa.taskmanager.dto;

import lombok.*;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private String timestamp;

}
