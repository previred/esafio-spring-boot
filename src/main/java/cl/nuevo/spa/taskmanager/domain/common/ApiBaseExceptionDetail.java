package cl.nuevo.spa.taskmanager.domain.common;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiBaseExceptionDetail {

  private String url;
  private String method;
  private String httpStatusCode;

  private String errorCode;
  private String message;
  private String debugMessage;
  private String errorDetail;
  private LocalDateTime localDateTime;
}
