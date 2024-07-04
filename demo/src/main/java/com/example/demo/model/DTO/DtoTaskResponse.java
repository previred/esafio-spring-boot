package com.example.demo.model.DTO;

import com.example.demo.configuration.response.GeneralResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoTaskResponse implements GeneralResponse {

    private Long idTask;
    private String nameTask;
    private String statusCode;
    private String username;


}
