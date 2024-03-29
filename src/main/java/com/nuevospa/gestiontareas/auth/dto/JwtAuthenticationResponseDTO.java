package com.nuevospa.gestiontareas.auth.dto;

public class JwtAuthenticationResponseDTO {
    private String tokenType = "Bearer";
    private String accessToken;

    public JwtAuthenticationResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
