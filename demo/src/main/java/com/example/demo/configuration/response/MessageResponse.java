package com.example.demo.configuration.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse implements GeneralResponse{


    //    @Schema(example = "mensaje de error", description = "describe el error ocurrido")
    private String message;
}
