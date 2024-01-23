package com.previred.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseToken {
    private int codigo;
    private String mensaje;
    private String token;
}
