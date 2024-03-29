package com.nuevospa.gestiontareas.util;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityUtilsTest {
    @Test
    void isValidEmailTest() {
        assertTrue(SecurityUtils.isValidEmail("test@example.com"));
        assertFalse(SecurityUtils.isValidEmail("testexample.com"));
        assertFalse(SecurityUtils.isValidEmail(null));
    }

    @Test
    void sanitizeStringTest() {
        assertEquals("test", SecurityUtils.sanitizeString("test"));
        assertEquals("&amp;", SecurityUtils.sanitizeString("&"));
        assertEquals("", SecurityUtils.sanitizeString(null));
    }

    @Test
    void sanitizeMapTest() {
        Map<String, String> inputMap = Map.of(
                "<script>", "alert('test')",
                "key", "<img src=x onerror=alert('img')>"
        );
        Map<String, String> sanitizedMap = SecurityUtils.sanitizeMap(inputMap);

        assertNotNull(sanitizedMap);
        assertTrue(sanitizedMap.containsKey("key"));
        assertFalse(sanitizedMap.containsKey("<script>"));
        assertFalse(sanitizedMap.containsValue("alert('test')"));
        assertFalse(sanitizedMap.containsValue("<img src=x onerror=alert('img')>"));
    }

    @Test
    void sanitizeUsuarioDTOTest() {
        UsuarioDTO input = UsuarioDTO.builder()
                .email("<script>alert('test')</script>")
                .nombre("<img src=x onerror=alert('img')>")
                .build();

        UsuarioDTO sanitized = SecurityUtils.sanitize(input);

        assertNotNull(sanitized);
        assertEquals("&lt;script&gt;alert(&#x27;test&#x27;)&lt;&#x2F;script&gt;", sanitized.getEmail());
        assertEquals("&lt;img src&#x3D;x onerror&#x3D;alert(&#x27;img&#x27;)&gt;", sanitized.getNombre());
    }

    @Test
    void sanitizeTareaDTOTest() {
        TareaDTO input = TareaDTO.builder()
                .descripcion("<script>alert('test')</script>")
                .usuario(null)
                .estadoTarea(null)
                .build();

        TareaDTO sanitized = SecurityUtils.sanitize(input);

        assertNotNull(sanitized);
        assertEquals("&lt;script&gt;alert(&#x27;test&#x27;)&lt;&#x2F;script&gt;", sanitized.getDescripcion());
        assertNull(sanitized.getUsuario());
        assertNull(sanitized.getEstadoTarea());
    }

    @Test
    void sanitizeEstadoTareaDTOTest() {
        EstadoTareaDTO input = EstadoTareaDTO.builder()
                .nombre("<script>alert('test')</script>")
                .build();

        EstadoTareaDTO sanitized = SecurityUtils.sanitize(input);

        assertNotNull(sanitized);
        assertEquals("&lt;script&gt;alert(&#x27;test&#x27;)&lt;&#x2F;script&gt;", sanitized.getNombre());
    }
}
