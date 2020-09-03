package com.lottomatic.springbootweb.config.auth;

import com.lottomatic.springbootweb.config.auth.dto.OAuthAttributes;
import com.lottomatic.springbootweb.config.auth.dto.SessionUser;
import com.lottomatic.springbootweb.domain.user.User;
import com.lottomatic.springbootweb.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    /*
        Created By : m_veloper

        [ 코드설명 ]

        registrationId
            - 현재 로그인 진행 중인 서비스를 구분하는 코드입니다.
            - 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용합니다.

        userNameAttributeName
            - OAuth2 로그인 진행 시 키가 되는 필드값을 이야기합니다. Primary Key와 같은 의미입니다.
            - 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않습니다. 구글의 기본 코드는 "sub"입니다.

        OAuthAttributes
            - OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스입니다.
            - 이후 네이버 등 다른 소셜 로그인도 이 클래스 사용합니다.
            - 바로 아래에서 이 클래스의 코드가 나오니 차례로 생성하시면 됩니다.

        SessionUser
            - 세션에 사용자 정보를 저장하기 위한 Dto 클래스입니다.

        구글 사용자 정보가 업데이트 되었을 때를 대비하여 update 기능도 같이 구현되었습니다.
        사용자의 이름이나 프로필 사진이 변경되면 User엔티티에도 반영됩니다.
    */
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
