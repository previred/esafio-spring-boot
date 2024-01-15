package com.challenge.spa.adapter.out.exception.data;

public record ErrorResponse(
        Integer code,
        String message
) {
}
