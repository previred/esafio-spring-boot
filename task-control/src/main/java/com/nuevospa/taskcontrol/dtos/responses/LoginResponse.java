package com.nuevospa.taskcontrol.dtos.responses;

import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String accessToken;

    private Usuario usuario;
}
