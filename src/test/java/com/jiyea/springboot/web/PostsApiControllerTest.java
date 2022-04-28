package com.jiyea.springboot.web;

import com.jiyea.springboot.domain.posts.Posts;
import com.jiyea.springboot.domain.posts.PostsRepository;
import com.jiyea.springboot.web.dto.PostsResponseDto;
import com.jiyea.springboot.web.dto.PostsSaveRequestDto;
import com.jiyea.springboot.web.dto.PostsUpdateRequestDto;
import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 랜덤포트 실행
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_등록된다() throws Exception {
        String title = "제목이다";
        String content = "내용이다";
        String author = "jiyea";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void post_수정된다() throws Exception {
        Posts saved = postsRepository.save(Posts.builder()
                .title("t")
                .content("c")
                .author("a")
                .build());

        Long updateid = saved.getId();
        String title = "et";
        String content = "ec";

        PostsUpdateRequestDto updateRequestDto =
                PostsUpdateRequestDto.builder()
                .title(title).content(content).build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateid;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(updateRequestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    public void basetimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2022, 4, 28, 0 ,0,0);
        postsRepository.save(Posts.builder()
        .title("tttt")
        .content("cccc")
        .author("aaaa")
        .build());

        List<Posts> all = postsRepository.findAll();
        Posts post = all.get(0);

        System.out.println(">>>>>>>> created " + post.getCreatedDate()+" >>modi " + post.getModifiedDate());
        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }

}
