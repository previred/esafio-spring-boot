package jlillor.ms.tasks.manager.exceptions;

import jlillor.ms.tasks.manager.dtos.Message;
import lombok.Getter;

import java.io.Serial;

/**
 * Excepción para cuando no se encuentra un recurso.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Getter
public class MsInternalError extends RuntimeException {

    /** Serial. */
    @Serial
    private static final long serialVersionUID = -1899578028736249752L;
    /** Código de error. */
    private final String code;

    // --------------------------------------------------------------------------------------
    // -- Constructores ---------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @param message {@link String} mensaje de error.
     * @param code {@link String} código de error.
     */
    public MsInternalError(final String message, final String code) {
        super(message);
        this.code = code;

    }

    /**
     * Constructor.
     *
     * @param message {@link Message} mensaje de error.
     */
    public MsInternalError(Message message) {
        this(message.getMessage(), message.getCode());
    }


}
