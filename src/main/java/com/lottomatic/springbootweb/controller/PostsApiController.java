package com.lottomatic.springbootweb.controller;

import com.lottomatic.springbootweb.dto.posts.PostsSaveRequestDto;
import com.lottomatic.springbootweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

}
