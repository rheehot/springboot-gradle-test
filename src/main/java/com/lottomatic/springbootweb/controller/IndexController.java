package com.lottomatic.springbootweb.controller;

import com.lottomatic.springbootweb.config.auth.LoginUser;
import com.lottomatic.springbootweb.config.auth.dto.SessionUser;
import com.lottomatic.springbootweb.dto.posts.PostsResponseDto;
import com.lottomatic.springbootweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    /*
        [ REST 에서 CRUD 는 다음과 같이 HTTP Method 에 매핑 ]

        생성(Create) - POST
        읽기(Read) - GET
        수정(Update) - PUT
        삭제(Delete) - DELETE
    */

    private final PostService postService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());

        /*
            (SessionUser) httpSession.getAttribute("user")
                - 앞서 작선된 CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장하도록 구성했습니다.
                - 즉, 로그인 성공시 httpSerssion.getAttribute("user")에서 값을 가져올 수 있습니다.
            SessionUser user = (SessionUser) httpSession.getAttribute("user");

            if(user != null)
                - 세션에 저장된 값이 있을 때만 model 에 userName 으로 등록

            @LoginUser SessionUser user
                - 기존에 (User)httpSerssion.getAttribut("user")로 가져오던 세션 정보 값이 개선되었습니다.
                - 이제는 어느 컨트롤러든지 @LoginUser 만 사용하면 세션 정보를 가져올 수 있게 되었습니다.
        */

        if(user != null){
//            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}