package mtak.dev.helloSpring.web;

import lombok.RequiredArgsConstructor;
import mtak.dev.helloSpring.service.PostsService;
import mtak.dev.helloSpring.web.dto.PostsReponseDto;
import mtak.dev.helloSpring.web.dto.PostsSaveRequestDto;
import mtak.dev.helloSpring.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
/*
 final이■선언된■모든■ 필드를 인자값으로 하는 생성자
 */
@RestController
public class PostsApiController {

  private final PostsService postsService;

  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto requestDto) {
    return postsService.save(requestDto);
  }

  @PutMapping("/api/v1/posts/{id}")
  public Long update(@PathVariable Long id,
      @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
    return postsService.update(id, postsUpdateRequestDto);
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostsReponseDto findById(@PathVariable Long id) {
    return postsService.findById(id);
  }
}
