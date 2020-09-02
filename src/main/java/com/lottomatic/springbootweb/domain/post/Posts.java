package com.lottomatic.springbootweb.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    /*
        스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
