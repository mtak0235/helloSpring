package mtak.dev.helloSpring.web;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import mtak.dev.helloSpring.domain.posts.Posts;
import mtak.dev.helloSpring.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @After
  public void tearDown() {
    postsRepository.deleteAll();
  }

  Posts posts;

  @Before
  public void saveFake() {

  }

  @Test
  void getPost() throws IOException, ClassNotFoundException {
    posts = postsRepository.save(Posts.builder()
                                      .title("title")
                                      .content("content")
                                      .author("mtak")
                                      .build());
    String url = "http://localhost:" + port + "/api/v2/posts/" + posts.getId()
                                                                      .toString();
    ResponseEntity<InputStream> response = restTemplate.exchange(url, HttpMethod.GET,
        new HttpEntity<>(new HttpHeaders()),
        InputStream.class);
    InputStream in = response.getBody();
    BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
    ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
    System.out.println(
        "objectInputStream.readObject().toString() = " + objectInputStream.readObject()
                                                                          .toString());
//    System.out.println("response.getBody() = " + in);
  }
}