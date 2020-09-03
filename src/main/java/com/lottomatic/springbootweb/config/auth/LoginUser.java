package com.lottomatic.springbootweb.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

    /*
        Created By : m_veloper

        [ 코드설명 ]

        @Target(ElementType.PARAMETER)
            이 어노테이션이 생성될 수 있는 위치를 지정합니다.
            PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다.
            이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있습니다.

        @interface
            이 파일을 어노테이션 클래스로 지정합니다.
            LoginUser 라는 이름을 가진 어노테이션이 생성되었다고 보면 됩니다.

        @Retention(RetentionPolicy.RUNTIME)
            컴파일 이후에도 JVM 에 의해서 참조가 가능하다.

    */
}
