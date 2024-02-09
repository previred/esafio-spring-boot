package com.previred.desafiobackend.domain.dto.user.response;

import com.previred.desafiobackend.domain.dto.enums.RoleEnum;
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
public class GetUser {

    private String email;
    private RoleEnum role;

}
