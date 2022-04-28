package com.jiyea.springboot.service.posts;

import com.jiyea.springboot.domain.posts.PostsRepository;
import com.jiyea.springboot.domain.posts.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return 1L;
//        return postsRepository.save(requestDto);
    }
}
