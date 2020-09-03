package com.lottomatic.springbootweb.config.auth.dto;

import com.lottomatic.springbootweb.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    /*
        Created By : m_veloper

        [ 코드설명 ]

        SessionUser 에는 인증된 사용자 정보만 필요합니다.

        @Entity User 클래스를 SessionUser 로 사용안하는 이유
            - 세션에 저장하기 위해 User 클래스를 세션에 저장하려고 하니 User 클래스에 직렬화를 구현하지 않았다는 에러가 난다.
            - Entity 클래스는 직렬화 코드를 넣지 않는게 좋다
            - 엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모른다.
            - @OneToMany, @ManyToMany 등 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지 포함되니 성능 이슈, 부수 효과가 발생할 확률이 높다
            - 그래서 직렬화 기능을 가진 세션 Dto 를 하나 추가로 만든 것이 더 좋은 방법이다.
    */

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
