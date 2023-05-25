package mtak.dev.helloSpring.web.converter;

import mtak.dev.helloSpring.web.dto.ApiResponse;
import org.springframework.http.HttpStatus;

public class ApiResponseGenerator {

  public static <D> ApiResponse<ApiResponse.SuccessBody<D>> success(D data, HttpStatus status,
      MessageCode messageCode) {
    return new ApiResponse<>(
        new ApiResponse.SuccessBody<>(data, messageCode.getCode(), messageCode.getCodeDescription())
        , status);
  }

}
