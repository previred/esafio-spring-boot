package jlillor.ms.tasks.manager.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * La clase MsResponse.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"code", "message", "timestamp", "data"})
public class MsResponse<T>  {

	/** El código de respuesta. */
	private String code;
	/** El mensaje de respuesta. */
	private String message;
	/** La fecha y hora de la respuesta. */
	private LocalDateTime timestamp;
	/** El objeto de respuesta. */
	private T data;

	// -------------------------------------------------------------------------------------
	// -- Constructores --------------------------------------------------------------------
	// -------------------------------------------------------------------------------------
	/**
	 * Constructor de la clase.
	 *
	 * @param code    {@link String} El código de respuesta.
	 * @param message {@link String} El mensaje de respuesta.
	 */
	public MsResponse(String code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param code    {@link String} El código de respuesta.
	 * @param message {@link String} El mensaje de respuesta.
	 * @param data    {@link T} El objeto de respuesta.
	 */
	public MsResponse(String code, String message, T data) {
		this(code, message);
		this.data = data;
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param msg {@link Message} mensaje de respuesta.
	 */
	public MsResponse(Message msg) {
		this(msg.getCode(), msg.getMessage());
	}

	/**
	 * Constructor de la clase.
	 *
	 * @param msg {@link Message} mensaje de respuesta.
	 * @param data {@link T} El objeto de respuesta.
	 */
	public MsResponse(Message msg, T data) {
		this(msg.getCode(), msg.getMessage(), data);
	}

}
