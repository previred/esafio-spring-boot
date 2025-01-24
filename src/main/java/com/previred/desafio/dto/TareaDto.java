package com.previred.desafio.dto;

import lombok.Data;

/**
 * TareaDto.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */
@Data
public class TareaDto {
    private Long id;
    private String description;
    private Long statusId;
}
