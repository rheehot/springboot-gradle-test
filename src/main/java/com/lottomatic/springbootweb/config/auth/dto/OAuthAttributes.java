package com.lottomatic.springbootweb.config.auth.dto;

import com.lottomatic.springbootweb.domain.user.Role;
import com.lottomatic.springbootweb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    /*
        Created By : m_veloper

        [ 코드설명 ]

        of()
            - OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야만 합니다.

        toEntity
        - User 엔티티를 생성합니다.
        - OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때입니다.
        - 가입할 때의 기본 권할을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용합니다.

    */
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String gender;
    private String age;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String gender, String age, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey= nameAttributeKey;
        this.name = name;
        this.email = email;
        this. gender = gender;
        this.age = age;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {

        switch (registrationId){
            case "kakao":
                return ofKakao("id", attributes);
            case "naver":
                return ofNaver("id", attributes);
        }
//        if("naver".equals(registrationId)) {
//            return ofNaver("id", attributes);
//        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");

        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .gender((String) response.get("gender"))
                .age((String) response.get("age_range"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUSET)
                .build();
    }
}
