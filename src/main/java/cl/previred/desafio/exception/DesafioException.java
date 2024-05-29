package cl.previred.desafio.exception;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesafioException {
    private String message;
}