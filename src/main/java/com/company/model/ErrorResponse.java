package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String codError;
    private String messageError;

}
