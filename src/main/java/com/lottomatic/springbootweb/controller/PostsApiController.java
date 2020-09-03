package com.lottomatic.springbootweb.controller;

import com.lottomatic.springbootweb.dto.posts.PostsResponseDto;
import com.lottomatic.springbootweb.dto.posts.PostsSaveRequestDto;
import com.lottomatic.springbootweb.dto.posts.PostsUpdateRequestDto;
import com.lottomatic.springbootweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    /*
        [ REST 에서 CRUD 는 다음과 같이 HTTP Method 에 매핑 ]

        생성(Create) - POST
        읽기(Read) - GET
        수정(Update) - PUT
        삭제(Delete) - DELETE
    */
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody
            PostsUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {

        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

}
