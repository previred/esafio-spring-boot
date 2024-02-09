package com.previred.desafiobackend.domain.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {

    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String role;

}
