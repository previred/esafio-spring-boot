package com.test.previred.domain.model.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class User {
    private String name;
    private String email;
    private String password;
}
