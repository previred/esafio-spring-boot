package com.nuevospa.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SignInResponseDto {
    private String accessToken;
    private String expirationDate;
    @Builder.Default
    private String tokenType = "Bearer";
}