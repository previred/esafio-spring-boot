package ar.com.challenge.desafio_spring_boot.dto.request;

import ar.com.challenge.desafio_spring_boot.dto.SignupDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotNull
    SignupDto signupDto;
}
