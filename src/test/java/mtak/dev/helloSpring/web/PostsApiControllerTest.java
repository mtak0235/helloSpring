package mtak.dev.helloSpring.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import mtak.dev.helloSpring.domain.posts.Posts;
import mtak.dev.helloSpring.domain.posts.PostsRepository;
import mtak.dev.helloSpring.web.dto.PostsSaveRequestDto;
import mtak.dev.helloSpring.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    /*
    JPA가능까지 한꺼번에 테스트할 때 사용
     */
class PostsApiControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @After
  public void tearDown() {
    //postsRepository.deleteAll();
  }

  @Test
  void save() {
    String title = "titile";
    String content = "content";
    PostsSaveRequestDto reqDto = PostsSaveRequestDto.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .author("mtak")
                                                    .build();
    String url = "http://localhost:" + port + "/api/v1/posts";
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, reqDto, Long.class);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);
    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0)
                  .getTitle()).isEqualTo(title);
    assertThat(all.get(0)
                  .getContent()).isEqualTo(content);
  }

  @Test
  void update() {
    Posts posts = postsRepository.save(Posts.builder()
                                            .title("title")
                                            .content("content")
                                            .author("mtak")
                                            .build());
    Long updatedId = posts.getId();
    String exTitle = "title2";
    String exContent = "content2";
    PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                                                            .title(exTitle)
                                                            .content(exContent)
                                                            .build();
    String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;
    HttpEntity<PostsUpdateRequestDto> entity = new HttpEntity<>(
        requestDto);
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity,
        Long.class);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);
    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0)
                  .getTitle()).isEqualTo(exTitle);
    assertThat(all.get(0)
                  .getContent()).isEqualTo(exContent);

  }

  @Test
  void findById() {
  }
}