package com.test.previred.infrastructure.rest.controller.common;

import java.util.HashMap;
import java.util.Map;

public class Commons {
    Map<String, String> typicalErrors = new HashMap<>();
    public static final String APP_NAME = "SAF";

    public Commons() {
        typicalErrors.put("404", "NO SE ENCUENTA EL RECURSO");
        typicalErrors.put("400", "HUBO UN ERROR EN LA SOLICITUD");
    }

    public Map<String, String> getTypicalErrors() {
        return typicalErrors;
    }
}