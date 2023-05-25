package mtak.dev.helloSpring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//entity로 db를 접근하게 해줌. Dao라고 불림.
//Entity 클래스와 기본 Entity Repository는 함께 위치해야함.
// 기본적인 CRUD 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
