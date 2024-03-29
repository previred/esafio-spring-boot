package com.nuevospa.gestiontareas.util;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SecurityUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final String EMPTY_STRING = "";
    private SecurityUtils() {
        //
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static String sanitizeString(String input) {
        try {
            // Escapar caracteres HTML para prevenir inyecciones HTML
            input = input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;")
                    .replace("=", "&#x3D;")
                    .replace("/", "&#x2F;");

            // Eliminar etiquetas y atributos que no son de formateo básico
            // Esto es una simplificación y no captura todas las posibles inyecciones de HTML/JS
            input = input.replaceAll("<(script|iframe|style|img)[^>]*?>.*?</\\1>", "") // Elimina scripts, iframes, styles, imgs
                    .replaceAll("<[^>]+>", ""); // Elimina cualquier otra etiqueta

            return input;
        } catch (NullPointerException e) {
            return EMPTY_STRING;
        }
    }
    public static Map<String, String> sanitizeMap(Map<String, String> inputMap) {
        Map<String, String> sanitizedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : inputMap.entrySet()) {
            String key = sanitizeString(entry.getKey());
            String value = sanitizeString(entry.getValue());
            if (key != null && value != null) {
                sanitizedMap.put(key, value);
            }
        }
        return sanitizedMap;
    }

    public static UsuarioDTO sanitize(UsuarioDTO input) {
        if (input == null) {
            return null;
        }

        return UsuarioDTO.builder()
                .id(input.getId())
                .email(sanitizeString(input.getEmail()))
                .nombre(sanitizeString(input.getNombre()))
                .password(sanitizeString(input.getPassword()))
                .build();
    }

    public static TareaDTO sanitize(TareaDTO input) {
        if (input == null) {
            return null;
        }

        return TareaDTO.builder()
                .id(input.getId())
                .descripcion(sanitizeString(input.getDescripcion()))
                .usuario(sanitize(input.getUsuario()))
                .estadoTarea(sanitize(input.getEstadoTarea()))
                .build();
    }

    public static EstadoTareaDTO sanitize(EstadoTareaDTO input) {
        if (input == null) {
            return null;
        }

        return EstadoTareaDTO.builder()
                .id(input.getId())
                .nombre(sanitizeString(input.getNombre()))
                .build();
    }
}
