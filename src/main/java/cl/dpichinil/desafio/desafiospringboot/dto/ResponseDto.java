package cl.dpichinil.desafio.desafiospringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    public ResponseDto(int code) {
        this.code = code;
    }
    public ResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
    private Object data;
}
