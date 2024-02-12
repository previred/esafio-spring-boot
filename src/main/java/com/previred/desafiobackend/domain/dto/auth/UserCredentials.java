package com.previred.desafiobackend.domain.dto.auth;

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
public class UserCredentials {

    private String userEmail;

}
