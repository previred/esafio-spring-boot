package cl.previred.challenge.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class ResponseHandler {

    public <R> ResponseEntity<ResponseWrapper<R>> wrapResponse(Supplier<R> supplier) {
        try {
            return buildResponse(supplier.get());
        } catch (RuntimeException e) {
            throw new RestException(buildError(e), e);
        }
    }

    public <R> ResponseEntity<ResponseWrapper<Void>> wrapWithNoResponse(Supplier<R> supplier) {
        try {
            supplier.get();
            return buildResponse(null);
        } catch (RuntimeException e) {
            throw new RestException(buildError(e), e);
        }
    }

    private <R> ResponseEntity<ResponseWrapper<R>> buildResponse(R data) {
        return Optional.ofNullable(data)
                .map(d -> ResponseEntity.ok(new ResponseWrapper<R>(LocalDateTime.now(), d))
                ).orElse(ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new ResponseWrapper<R>(LocalDateTime.now(), null)));
    }

    private ErrorResponse buildError(RuntimeException e) {
        return new ErrorResponse(LocalDateTime.now(), e.getMessage());
    }


}
