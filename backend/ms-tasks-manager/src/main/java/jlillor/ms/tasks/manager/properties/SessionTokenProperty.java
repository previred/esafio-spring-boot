package jlillor.ms.tasks.manager.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Propiedades de la configuración del token.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Getter
@Setter
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "ms.security.jwt")
public class SessionTokenProperty {

    /** Clave secreta para firmar el token. */
    private String key;
    /** Tiempo de vida del token en milisegundos. */
    private long lifeTime;

}
