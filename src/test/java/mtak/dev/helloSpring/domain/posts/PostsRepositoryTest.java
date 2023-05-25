package mtak.dev.helloSpring.domain.posts;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  /*
   Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정 •보통은배포전전체테스트를수행할때테스트간데이터침범을막기위해사용합
니다.
• 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남
아있어다음테스트실행시테스트가실패할수있습니다.
   */
  @AfterEach
  void tearDown() {
    postsRepository.deleteAll();
  }

  @Test
  public void 게시글_저장_불러오기() {
    String title = "title";
    String content = "contente";

    /*
    • 테이블 posts에 insert/update 쿼리를 실행합니다.
    • id 값이 있다면 update가, 없다면 insert 쿼리가 실행됩니다.

     */
    postsRepository.save(Posts.builder()
                              .title(title)
                              .content(content)
                              .author("mtak")
                              .build());
    /*
     테이블 posts에 있는 모든 데이터를 조회해오는 메소드입니다.
     */
    List<Posts> postsList = postsRepository.findAll();
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }
}