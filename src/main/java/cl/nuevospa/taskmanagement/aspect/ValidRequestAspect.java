package cl.nuevospa.taskmanagement.aspect;

import cl.nuevospa.taskmanagement.dto.MessageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Log4j2
@Aspect
@Component
public class ValidRequestAspect {

    @Around("@annotation(cl.nuevospa.taskmanagement.annotation.ValidRequest)")
    public Object handleValidRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    log.error("Error in request parameters: {}", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
                    MessageResponseDTO errorMessage = MessageResponseDTO.builder()
                            .code("400")
                            .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                            .message("Parametro invalido [" + bindingResult.getFieldError().getField() + "]: "
                            + bindingResult.getFieldError().getDefaultMessage())
                            .build();
                    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
                }
            }
        }
        return joinPoint.proceed();
    }
}

