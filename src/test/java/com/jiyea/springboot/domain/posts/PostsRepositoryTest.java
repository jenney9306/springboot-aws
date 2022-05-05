package com.jiyea.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 사용할 경우 h2 DB 를 자동으로 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //junit 에서 단위 테스트가 끝날때마다 수행되는 메소드를 지정
    // 보통은 배포 전 전체 테스트를 수행할때 테스트 간 데이터 침범을 막기위해 사용
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("test@gmail.com")
                        .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
