package com.test.previred.domain.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ObjectTechMsg {
    private final String actionName;
    private final String serviceName;

    private final String transactionId;
    private final Object message;
}