package com.arturo.desafio_spring_boot.helpers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ErrorResponseObjectHelper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String toJsonString(Integer status, String error, String message) throws JsonProcessingException{
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", status);
        responseBody.put("error", error);
        responseBody.put("message", message);

        return this.objectMapper.writeValueAsString(responseBody);
    }
}
