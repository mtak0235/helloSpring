package mtak.dev.helloSpring.web;

import lombok.RequiredArgsConstructor;
import mtak.dev.helloSpring.service.PostsService;
import mtak.dev.helloSpring.web.converter.ApiResponseGenerator;
import mtak.dev.helloSpring.web.converter.MessageCode;
import mtak.dev.helloSpring.web.dto.ApiResponse;
import mtak.dev.helloSpring.web.dto.ApiResponse.SuccessBody;
import mtak.dev.helloSpring.web.dto.PostsReponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiController {

  private final PostsService postsService;

  @GetMapping("/api/v2/posts/{id}")
  public ApiResponse<SuccessBody<PostsReponseDto>> getPost(@PathVariable Long id) {
    PostsReponseDto post = postsService.findById(id);
    return ApiResponseGenerator.success(post, HttpStatus.OK, MessageCode.SUCCESS);
  }

}
