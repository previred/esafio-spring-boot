package jlillor.ms.tasks.manager.properties;

import jlillor.ms.tasks.manager.dtos.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Propiedades de los mensajes.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Setter
@Getter
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "ms.messages")
public class MessageProperty {

    /** Mensajes de éxito. */
    private Message success;
    /** Mensajes genérico de error. */
    private Message genericError;
    /** Mensajes de error de validación de login. */
    private Message invalidLogin;
    /** Mensajes de error de validación del token de sesión. */
    private Message invalidToken;
    /** Mensajes de error generico de no encontrado. */
    private Message genericNotFound;
    /** Mensajes de error para cuando no se puede leer el cuerpo de la petición. */
    private Message readableError;
    /** Mensajes de error de validacion de los campos. */
    private Message validationError;
    /** Mensajes de error de validacion del encabezado. */
    private Message headerNotFound;

}
