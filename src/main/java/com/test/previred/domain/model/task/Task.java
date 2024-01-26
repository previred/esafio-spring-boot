package com.test.previred.domain.model.task;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class Task {
    private String id;
    private String description;
    private String status;
}
