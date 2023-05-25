package mtak.dev.helloSpring.web.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<B> extends ResponseEntity<B> {

  public ApiResponse(HttpStatus status) {
    super(status);
  }

  public ApiResponse(B body, HttpStatus status) {
    super(body, status);
  }

  @AllArgsConstructor
  public static class SuccessBody<D> implements Serializable {

    private D data;
    private int code;
    private String codeDescription;
  }

  @AllArgsConstructor
  public static class FailureBody implements Serializable {

    private String code;
    private String message;

  }

}
