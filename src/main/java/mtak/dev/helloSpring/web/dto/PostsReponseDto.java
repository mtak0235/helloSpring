package mtak.dev.helloSpring.web.dto;

import lombok.Getter;
import mtak.dev.helloSpring.domain.posts.Posts;

@Getter
public class PostsReponseDto {

  private Long id;
  private String title;
  private String content;
  private String author;

  public PostsReponseDto(Posts ent) {
    this.id = ent.getId();
    this.title = ent.getTitle();
    this.content = ent.getContent();
    this.author = ent.getAuthor();

  }
}
