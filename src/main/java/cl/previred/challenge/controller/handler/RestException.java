package cl.previred.challenge.controller.handler;

public class RestException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public RestException(ErrorResponse errorResponse, RuntimeException e) {
        super(e);
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
