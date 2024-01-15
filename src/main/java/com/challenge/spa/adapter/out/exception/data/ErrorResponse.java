package com.challenge.spa.adapter.out.exception.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public record ErrorResponse(
        Integer code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> extraFields
) {
}
