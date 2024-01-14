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
@ConfigurationProperties(prefix = "ms.security.regexp")
public class RegexpProperty {

    /** Expresión regular para validar el email. */
    private String email;
    /** Expresión regular para validar el password. */
    private String password;

}
