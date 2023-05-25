package mtak.dev.helloSpring.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mtak.dev.helloSpring.domain.posts.Posts;
import mtak.dev.helloSpring.domain.posts.PostsRepository;
import mtak.dev.helloSpring.web.dto.PostsReponseDto;
import mtak.dev.helloSpring.web.dto.PostsSaveRequestDto;
import mtak.dev.helloSpring.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity())
                          .getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
    Posts posts = postsRepository.findById(id)
                                 .orElseThrow(
                                     () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
    //업데이트 쿼리 날리는 부분 없음 -> jpa영속성 컨텍스트 때문. 엔티티를 영구 저장하는 환경.
    // jpa의 entity manager가 활성화된 상태로 트랜잭션 안에서 디비 데이터 가져오면 데이터는 영속성 컨텍스트가 유지된 상태
    // 이 상태에서 데이터 값을 변겨앟면 트랜잭션이 끝날 때 해당 테이블의 변경부분을 반영함.-> dirty checking이라고 함.

    return id;
  }

  public PostsReponseDto findById(Long id) {
    Posts posts = postsRepository.findById(id)
                                 .orElseThrow(
                                     () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    return new PostsReponseDto(posts);
  }
}

