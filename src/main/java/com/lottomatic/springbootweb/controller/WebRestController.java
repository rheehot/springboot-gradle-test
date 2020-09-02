package com.lottomatic.springbootweb.controller;

import com.lottomatic.springbootweb.domain.WebRest;
import com.lottomatic.springbootweb.domain.post.PostsRepository;
import com.lottomatic.springbootweb.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class WebRestController {

    private final PostsRepository postsRepository;

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public WebRest webRest(@RequestParam("name") String name,
                            @RequestParam("amount") int amount) {
        return new WebRest(name, amount);
    }
}
