package mtak.dev.helloSpring.domain.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
/*
테이블과 링크될 클래스임을 나타냅니다.
•기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매
칭합니다.
•ex) SalesManager.java > sales_manager table
 */
public class Posts {

}
